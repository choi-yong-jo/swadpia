package kr.co.swadpia.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 공통 정규식 작업을 위한 유틸리티 클래스입니다.
 */
public class RegexUtils {

    private RegexUtils() {
        // 인스턴스화를 방지하기 위한 private 생성자
    }

    /**
     * 입력이 주어진 정규식 패턴과 일치하는지 검증합니다.
     *
     * @param input 검증할 입력 문자열
     * @param regex 일치 여부를 확인할 정규식 패턴
     * @return 입력이 패턴과 일치하면 true, 그렇지 않으면 false
     */
    public static boolean matches(String input, String regex) {
        if (input == null || regex == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    /**
     * 입력이 주어진 정규식 패턴과 일치하는 하위 문자열을 포함하는지 확인합니다.
     *
     * @param input 확인할 입력 문자열
     * @param regex 검색할 정규식 패턴
     * @return 입력이 패턴과 일치하는 하위 문자열을 포함하면 true, 그렇지 않으면 false
     */
    public static boolean contains(String input, String regex) {
        if (input == null || regex == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    /**
     * 입력 문자열에서 정규식 패턴에 일치하는 모든 부분을 대체 문자열로 변경합니다.
     *
     * @param input       처리할 입력 문자열
     * @param regex       대체할 정규식 패턴
     * @param replacement 대체할 문자열
     * @return 대체가 완료된 결과 문자열
     */
    public static String replaceAll(String input, String regex, String replacement) {
        if (input == null || regex == null || replacement == null) {
            return input;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll(replacement);
    }

    /**
     * 주어진 정규식 패턴과 일치하는 첫 번째 하위 문자열을 추출합니다.
     *
     * @param input 처리할 입력 문자열
     * @param regex 검색할 정규식 패턴
     * @return 첫 번째 일치하는 하위 문자열, 일치하는 문자열이 없으면 null
     */
    public static String extractFirst(String input, String regex) {
        if (input == null || regex == null) {
            return null;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    /**
     * 주어진 정규식 패턴을 기준으로 입력 문자열을 분할합니다.
     *
     * @param input 분할할 입력 문자열
     * @param regex 분할할 정규식 패턴
     * @return 패턴을 기준으로 분할된 문자열 배열
     */
    public static String[] split(String input, String regex) {
        if (input == null || regex == null) {
            return new String[0];
        }
        Pattern pattern = Pattern.compile(regex);
        return pattern.split(input);
    }

    /**
     * 이메일 주소 형식을 검증합니다.
     *
     * @param email 검증할 이메일 주소
     * @return 이메일 주소가 유효하면 true, 그렇지 않으면 false
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return matches(email, emailRegex);
    }

    /**
     * 전화번호 형식을 검증합니다. 형식은 {2,3}-{3,4}-{4}입니다.
     *
     * @param phoneNumber 검증할 전화번호
     * @return 전화번호가 유효하면 true, 그렇지 않으면 false
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
        return matches(phoneNumber, phoneRegex);
    }

    private void example() {

        //예제
        String email = "example@example.com";
        String phoneNumber1 = "02-123-4567";
        String phoneNumber2 = "010-1234-5678";
        String phoneNumber3 = "123-456-7890"; // Invalid example

        // 이메일 검증
        boolean isEmailValid = RegexUtils.isValidEmail(email);
        System.out.println("이메일 유효성 검증: " + isEmailValid);

        // 전화번호 검증
        boolean isPhoneNumber1Valid = RegexUtils.isValidPhoneNumber(phoneNumber1);
        boolean isPhoneNumber2Valid = RegexUtils.isValidPhoneNumber(phoneNumber2);
        boolean isPhoneNumber3Valid = RegexUtils.isValidPhoneNumber(phoneNumber3);

        System.out.println("전화번호 1 유효성 검증: " + isPhoneNumber1Valid);
        System.out.println("전화번호 2 유효성 검증: " + isPhoneNumber2Valid);
        System.out.println("전화번호 3 유효성 검증: " + isPhoneNumber3Valid);
    }

}
