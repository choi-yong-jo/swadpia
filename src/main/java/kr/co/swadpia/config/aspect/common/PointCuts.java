package kr.co.swadpia.config.aspect.common;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
    @Pointcut("execution(* kr.co.swadpia..*Service.*(..))")
    public void partnerServiceLayerExecution() {}

    @Pointcut("execution(* kr.co.swadpia.member..*Service.*(..))")
    public void memberServiceLayerExecution() {}

    @Pointcut("execution(* kr.co.swadpia.admin..*Service.*(..))")
    public void adminServiceLayerExecution() {}

    @Pointcut("@annotation(kr.co.swadpia.config.annotation.aspect.ExternalAPIMethodAop)")
    public void externalApiMethodExecution() {}

    @Pointcut("@target(kr.co.swadpia.config.annotation.aspect.IgnoreAop)")
    public void ignoreExecution() {}

    @Pointcut("@annotation(kr.co.swadpia.config.annotation.aspect.DoLogMethodAop)")
    public void doLogExecution() {}

    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void transactionalExecution() {}
}
