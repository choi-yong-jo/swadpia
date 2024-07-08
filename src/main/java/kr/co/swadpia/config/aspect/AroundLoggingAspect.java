package kr.co.swadpia.config.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.swadpia.config.annotation.aspect.DoLogMethodAop;
import kr.co.swadpia.config.aspect.common.AspectUtility;
import kr.co.swadpia.common.service.AspectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
@Slf4j
public class AroundLoggingAspect {

    private final AspectService aspectService;
    private final ObjectMapper objectMapper;

    /**
     * ExternalAPIMethodAop 어노테이션 advisor
     *
     * @param proceedingJoinPoint 조인포인트
     */
    @Around(value = "kr.co.swadpia.config.aspect.common.PointCuts.externalApiMethodExecution()")
    public Object logExternalAPI(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String paramJsonStr = "";
        String responseJsonStr = "";
        Object result;
        long startTimeMs = System.currentTimeMillis();

        try {
            paramJsonStr = objectMapper.writeValueAsString(proceedingJoinPoint.getArgs());
        } catch (JsonProcessingException jsonProcessingException) {
            paramJsonStr = "파라메터 변환 실패 :" + jsonProcessingException.getMessage();
        }

        try {
            result = proceedingJoinPoint.proceed();
            responseJsonStr = objectMapper.writeValueAsString(result);
            return result;
        } catch (Exception e) {
            responseJsonStr = e.getMessage();
            //log.error("ExternalAPI AOP CALL Error {} ", ExceptionUtils.getStackTrace(e));
            log.error("ExternalAPI AOP CALL Error {} ", e.getMessage());
            throw e;
        } finally {
            aspectService.saveLogExternalAPI(proceedingJoinPoint, responseJsonStr, paramJsonStr, System.currentTimeMillis()-startTimeMs);
        }

    }

    @Around(value = "@annotation(doLogMethodAop)")
    public Object doLog(ProceedingJoinPoint proceedingJoinPoint, DoLogMethodAop doLogMethodAop) throws Throwable {

        try {
            long startTimeMs = System.currentTimeMillis();

            boolean anoParam = doLogMethodAop.param();
            boolean anoLeadTime = doLogMethodAop.leadTime();
            boolean anoResult = doLogMethodAop.result();

            if(anoLeadTime){
                log.info("[{}]start time={}",proceedingJoinPoint.getSignature().toShortString(), AspectUtility.getCurrentDateTime());
            }

            if (anoParam) {
                log.info("[{}]parameter info={}", proceedingJoinPoint.getSignature().toShortString(), objectMapper.writeValueAsString(proceedingJoinPoint.getArgs()));
            }

            Object result = proceedingJoinPoint.proceed();

            if(anoLeadTime) {
                log.info("[{}]end time={} / lead time = {}ms"
                        , proceedingJoinPoint.getSignature().toShortString()
                        , AspectUtility.getCurrentDateTime()
                        , System.currentTimeMillis() - startTimeMs);
            }

            if(anoResult){
                log.info("[{}]return result info={}", proceedingJoinPoint.getSignature().toShortString(), objectMapper.writeValueAsString(result));
            }

            return result;
        } catch (Exception e) {
            log.error("doLog AOP CALL Error {} ", e.getMessage());
            throw e;
        }
    }
}