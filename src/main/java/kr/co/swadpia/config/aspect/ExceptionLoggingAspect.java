package kr.co.swadpia.config.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.swadpia.common.service.AspectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
@Slf4j
public class ExceptionLoggingAspect {
    private final AspectService aspectService;
    private final ObjectMapper objectMapper;

    /**
     * 파트너 와 어드민 exception Advisor
     * partnerService 와 adminService 중 externalAPIMethodAop 어노테이션은 제외
     *
     * @param joinPoint 조인포인트
     * @param ex        exception
     */
    @AfterThrowing(pointcut = "(kr.co.swadpia.config.aspect.common.PointCuts.partnerServiceLayerExecution() " +
            "|| kr.co.swadpia.config.aspect.common.PointCuts.adminServiceLayerExecution())" +
            "&& !kr.co.swadpia.config.aspect.common.PointCuts.externalApiMethodExecution()"
            , throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {

        log.error(ExceptionUtils.getStackTrace(ex));

        String paramJsonStr = "";
        try {
            paramJsonStr = objectMapper.writeValueAsString(joinPoint.getArgs());
        } catch (Exception e) {
            paramJsonStr = "파라메터 변환 실패";
        }

        //exception 로그 저장
        aspectService.saveLogException(joinPoint, ex, paramJsonStr);
    }
}