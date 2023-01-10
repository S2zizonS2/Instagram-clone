package my.dhlee.instagramclonebackend.user.dto.request;

import javax.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VerifyEmail {
    private String deviceId;
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    private String verifyCode;

    public VerifyEmail(final String deviceId, final String email, final String verifyCode) {
        this.deviceId = deviceId;
        this.email = email;
        this.verifyCode = verifyCode;
    }

    public VerifyEmail(final String deviceId, final String email) {
        this(deviceId, email, null);
    }
}
