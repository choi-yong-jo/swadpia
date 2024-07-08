package kr.co.swadpia.config.aspect.common;

import org.aspectj.lang.JoinPoint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AspectUtility {
    public static String getServiceArea(JoinPoint joinPoint) {

        String serviceArea;

        if (joinPoint.getTarget().getClass().getPackageName().contains("kr.co.swadpia.member")) {
            serviceArea = "MEMBER";
        } else if (joinPoint.getTarget().getClass().getPackageName().contains("kr.co.swadpia.admin")) {
            serviceArea = "ADMIN";
        } else if (joinPoint.getTarget().getClass().getPackageName().contains("kr.co.swadpia.partner")) {
            serviceArea = "PARTNER";
        } else {
            serviceArea = ""; //이거 케이스는 안나옴.
        }

        return serviceArea;
    }

    public static String getStringWithLimit(String context, int limit) {
        return context.substring(0, Math.min(context.length(), limit));
    }

    public static String getCurrentDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
