package my.dhlee.instagramclonebackend.user.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.dhlee.instagramclonebackend.user.domain.BirthDate;
import my.dhlee.instagramclonebackend.user.domain.User;
import org.springframework.util.ObjectUtils;

@NoArgsConstructor
@Getter
public class SignUpRequest {

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

    private int year;
    private int month;
    private int day;

    @Builder
    private SignUpRequest(
        final String email,
        final String phoneNumber,
        final String password,
        final String fullname,
        final String username,
        final int year,
        final int month,
        final int day
    ) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.fullname = fullname;
        this.username = username;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public void validate() {
        final AttemptRequest basicInfo = AttemptRequest.builder()
            .email(email)
            .phoneNumber(phoneNumber)
            .password(password)
            .fullname(fullname)
            .username(username)
            .build();

        basicInfo.validate();
        new BirthDateDto(year, month, day).validate();
    }

    public User toEntity() {
        final String userId = ObjectUtils.isEmpty(phoneNumber) ? email : phoneNumber;

        return User.builder()
            .userId(userId)
            .password(password)
            .username(username)
            .fullname(fullname)
            .birthDate(new BirthDate(year, month, day))
            .build();
    }
}
