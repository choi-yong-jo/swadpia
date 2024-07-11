package kr.co.swadpia.common.service;

import kr.co.swadpia.config.annotation.aspect.ExternalAPIMethodAop;
import kr.co.swadpia.config.annotation.aspect.IgnoreAop;
import kr.co.swadpia.config.aspect.common.AspectUtility;
import kr.co.swadpia.common.entity.CommonExceptionHistory;
import kr.co.swadpia.common.entity.ExternalAPIHistory;
import kr.co.swadpia.repository.jpa.CommonExceptionHistoryRepository;
import kr.co.swadpia.repository.jpa.ExternalAPIHistoryRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
@IgnoreAop
public class AspectService {

    private CommonExceptionHistoryRepository commonExceptionHistoryRepository;
    private ExternalAPIHistoryRepository externalAPIHistoryRepository;

    /**
     * Exception 로그 저장
     *
     * @param joinPoint    조인포인트
     * @param ex           exception 정보
     * @param paramJsonStr 파라메터정보
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)

    public void saveLogException(JoinPoint joinPoint, Throwable ex, String paramJsonStr) {

        CommonExceptionHistory history
                = new CommonExceptionHistory(AspectUtility.getServiceArea(joinPoint)
                , joinPoint.getSignature().toShortString()
                , AspectUtility.getStringWithLimit(paramJsonStr, 3000)
                , ex.getClass().getSimpleName()
                , AspectUtility.getStringWithLimit(ex.getMessage(), 3000)
                , AspectUtility.getStringWithLimit(ExceptionUtils.getStackTrace(ex), 1000));

        commonExceptionHistoryRepository.save(history);
    }

    /**
     * 외부 API 로그 저장
     *
     * @param proceedingJoinPoint proceedingJoinPoint 조인포인트
     * @param response            api resultData
     * @param paramJsonStr        파라메너 JsonData
     * @param apiLeadTime         api 지연 시간
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveLogExternalAPI(ProceedingJoinPoint proceedingJoinPoint, String response, String paramJsonStr, long apiLeadTime) {

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();

        //ExternalAPIMethodAop 어노테이션의 value값을 들고오기 위해.
        ExternalAPIMethodAop methodAop = signature.getMethod().getAnnotation(ExternalAPIMethodAop.class);

        ExternalAPIHistory externalAPIHistory
                = new ExternalAPIHistory(AspectUtility.getServiceArea(proceedingJoinPoint)
                , (methodAop != null) ? methodAop.value() : ""
                , AspectUtility.getStringWithLimit(paramJsonStr, 4000)
                , AspectUtility.getStringWithLimit(response, 4000)
                , apiLeadTime
                , proceedingJoinPoint.getSignature().toShortString());

        externalAPIHistoryRepository.save(externalAPIHistory);
    }
}
