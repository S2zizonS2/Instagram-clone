package my.dhlee.instagramclonebackend.user.service;

import java.time.LocalDateTime;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.dhlee.instagramclonebackend.common.exception.ErrorCode;
import my.dhlee.instagramclonebackend.common.exception.InvalidValueException;
import my.dhlee.instagramclonebackend.user.domain.EmailAuth;
import my.dhlee.instagramclonebackend.user.dto.request.VerifyEmail;
import my.dhlee.instagramclonebackend.user.repository.EmailAuthRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@EnableAsync
@Transactional
@Service
public class MailService {

    private final EmailAuthRepository emailAuthRepository;
    private final JavaMailSender javaMailSender;

    private static final String SALT_CHARS = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Async
    public String sendEmail(final VerifyEmail verifyEmail) {
        final String verifyCode = createVerifyCode();
        emailAuthRepository.save(new EmailAuth(verifyEmail.getEmail(), verifyCode));

        // 첨부파일이 없기 때문에 MimeMessage 대신 SimpleMailMessage 사용
        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(verifyEmail.getEmail());
        simpleMailMessage.setSubject("인스타 클론코딩 회원가입 인증번호입니다.");
        simpleMailMessage.setText(verifyCode);

        javaMailSender.send(simpleMailMessage);

        return verifyCode;
    }

    private String createVerifyCode() {
        final StringBuffer verifyCode = new StringBuffer();
        final Random random = new Random(432923748L);

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt() * SALT_CHARS.length();
            verifyCode.append(SALT_CHARS.charAt(index));
        }

        return verifyCode.toString();
    }

    public void verify(final VerifyEmail verifyEmail) {
        final EmailAuth emailAuth = emailAuthRepository.findByEmailAndVerifyCodeAndExpiredFalseAndExpiredDateAfter(
                verifyEmail.getEmail(), verifyEmail.getVerifyCode(), LocalDateTime.now())
            .orElseThrow(() -> new InvalidValueException(ErrorCode.VERIFY_CODE_EXPIRED));

        emailAuth.expired();
    }
}
