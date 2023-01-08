package my.dhlee.instagramclonebackend.common.exception;

import org.springframework.http.HttpStatus;
public enum ErrorCode {

    USER_ID_COULD_NOT_BE_NULL(HttpStatus.BAD_REQUEST, "A001", "휴대폰 번호 또는 이메일 주소를 입력해주세요."),
    USER_ID_COULD_ONE_VALUE(HttpStatus.BAD_REQUEST, "A002", "휴대폰 번호와 이메일 주소를 동시에 입력할 수 없습니다."),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, "A003", "올바르지 않은 이메일 형식입니다."),
    INVALID_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "A004", "올바르지 않은 휴대폰 번호 형식입니다."),
    DUPLICATE_FULL_NAME(HttpStatus.BAD_REQUEST, "A005", "동일한 사용자 이름이 존재합니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "A006", "비밀번호는 영문 대소문자와 숫자 특수기호 포함 8자 이상, 12자 이하여야 합니다."),
    INVALID_BIRTH_DATE(HttpStatus.BAD_REQUEST, "A007", "올바르지 않은 날짜형식입니다."),
    NOT_ELIGIBILITY_AGE(HttpStatus.BAD_REQUEST, "A008", "만 14세 이상만 가입가능합니다."),
    WRONG_EMAIL_CODE(HttpStatus.BAD_REQUEST, "A301", "잘못된 인증번호입니다. 인증번호를 확인해주세요."),
    INVALID_VALUE(HttpStatus.BAD_REQUEST, "S001", "올바르지 않은 입력입니다."),

    ;



    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(final HttpStatus status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
