package kr.co.swadpia.config.aspect;

import kr.co.swadpia.config.aspect.common.TransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
@Slf4j
public class BeforeAfterAspect {

//    @Before("(" +
//                "kr.co.swadpia.config.aspect.common.PointCuts.partnerServiceLayerExecution()" +
//                "|| kr.co.swadpia.config.aspect.common.PointCuts.adminServiceLayerExecution()" +
//                "|| kr.co.swadpia.config.aspect.common.PointCuts.memberServiceLayerExecution()" +
//             ") && @annotation(transactional)")
    public void setServiceName(JoinPoint joinPoint, Transactional transactional) {

        log.info(" getgeteget {} {} ", transactional.readOnly(), TransactionContext.get());
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {} ", joinPoint.getSignature().toShortString());
        if (transactional.readOnly()) {
            return; // readOnly가 아닌 경우, 로직을 실행않음.
        }

        // 스레드 로컬 변수에 클래스명과 메소드명 저장
        TransactionContext.set(joinPoint.getSignature().toShortString());
    }

//    @After("(" +
//            "kr.co.swadpia.config.aspect.common.PointCuts.partnerServiceLayerExecution()" +
//            "|| kr.co.swadpia.config.aspect.common.PointCuts.adminServiceLayerExecution()" +
//            "|| kr.co.swadpia.config.aspect.common.PointCuts.memberServiceLayerExecution()" +
//            ") && @annotation(transactional)")
    public void logTransaction(JoinPoint joinPoint, Transactional transactional) {

        if (transactional.readOnly()) {
            return; // readOnly가 아닌 경우, 로직을 실행않음.
        }

        // 클리어 해줘야함.
        TransactionContext.clear();
        log.info("클리어 호출");
    }
}
