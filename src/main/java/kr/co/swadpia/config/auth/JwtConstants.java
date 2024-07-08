package kr.co.swadpia.config.auth;


public class JwtConstants {

    // Expiration Time
    public static final long MINUTE = 1000 * 60;
    public static final long HOUR = 60 * MINUTE;
    public static final long DAY = 24 * HOUR;
    public static final long MONTH = 30 * DAY;

    public static final long AT_EXP_TIME =  10* HOUR;
    public static final long RT_EXP_TIME = 6 *  MONTH;

    // Secret
    public static final String MEMBER_JWT_SECRET = "rlvmxmvldkfhrmdlstlzmflt";

    public static final String PARTNER_JWT_SECRET = "rlvmxmvldkfhrmdlstlzmflt";
    public static final String ADMIN_JWT_SECRET = "rlvmxlvldkADMINtlzmflt";

    // Header
    public static final String AT_HEADER = "accessToken";
    public static final String RT_HEADER = "refreshToken";
    public static final String TOKEN_HEADER_PREFIX = "Bearer ";
}
