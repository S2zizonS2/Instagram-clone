package my.dhlee.instagramclonebackend.user.domain;

import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class EmailAuth {

    @Id @GeneratedValue
    private Long id;

    private String email;

    private String verifyCode;
    private LocalDateTime expiredDate;
    private Boolean expired;

    public EmailAuth(final String email, final String verifyCode) {

        this.email = email;
        this.verifyCode = verifyCode;
        this.expiredDate = LocalDateTime.now().plusMinutes(10L);
        this.expired = Boolean.FALSE;
    }

    public void expired() {
        this.expired = Boolean.TRUE;
    }
}
