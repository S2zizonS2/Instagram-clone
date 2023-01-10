package my.dhlee.instagramclonebackend.user.dto.request;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.dhlee.instagramclonebackend.common.exception.ErrorCode;
import my.dhlee.instagramclonebackend.common.exception.InvalidValueException;

@NoArgsConstructor
@Getter
public class AttemptRequest implements Serializable {

    private static final long serialVersionUID = 149472L;

    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,12}$";
    private static final java.util.regex.Pattern PASSWORD_PATTERN = java.util.regex.Pattern.compile(PASSWORD_REGEX);

    @Pattern(regexp = "^|(.+)@(\\S+)$", message = "올바르지 않은 이메일 형식입니다.")
    private String email;

    @Pattern(regexp = "^|01(?:0|1[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "올바르지 않은 휴대폰 번호 형식입니다.")
    private String phoneNumber;

    @Pattern(regexp = "^|(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,12}$", message = "비밀번호는 영문 대소문자와 숫자 특수기호 포함 8자 이상, 12자 이하여야 합니다.")
    private String password;

    @NotBlank(message = "성명을 입력해주세요.")
    private String fullname;

    @NotBlank(message = "사용자 이름을 입력해주세요.")
    private String username;

    public void validate() {
        validateUserId();
        validatePassword();
    }

    public void validateUserId() {
        isBothNullEmailAndPhoneNumber();
        isBothNotNullEmailAndPhoneNumber();
    }

    private void isBothNullEmailAndPhoneNumber() {
        if (Objects.isNull(email) && Objects.isNull(phoneNumber)) {
            throw new InvalidValueException(ErrorCode.USER_ID_COULD_NOT_BE_NULL);
        }
    }

    private void isBothNotNullEmailAndPhoneNumber() {
        if (Objects.nonNull(email) && Objects.nonNull(phoneNumber)) {
            throw new InvalidValueException(ErrorCode.USER_ID_COULD_ONE_VALUE);
        }
    }

    private void validatePassword() {
        final boolean isValidPassword = PASSWORD_PATTERN.matcher(this.password).matches();

        if (!isValidPassword) {
            throw new InvalidValueException(ErrorCode.INVALID_PASSWORD);
        }
    }

    @Builder
    private AttemptRequest(
        final String email,
        final String phoneNumber,
        final String password,
        final String fullname,
        final String username
    ) {

        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.fullname = fullname;
        this.username = username;
    }

    public String getUserId() {
        return Objects.nonNull(email) ? email : phoneNumber;
    }

}
