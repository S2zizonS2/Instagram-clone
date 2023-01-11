package my.dhlee.instagramclonebackend.user.service;

import java.time.LocalDateTime;
import java.util.UUID;
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

    @Async
    public void sendEmail(final VerifyEmail verifyEmail) {
        final String verifyCode = createVerifyCode();
        emailAuthRepository.save(new EmailAuth(verifyEmail.getEmail(), verifyCode));

        // 첨부파일이 없기 때문에 MimeMessage 대신 SimpleMailMessage 사용
        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(verifyEmail.getEmail());
        simpleMailMessage.setSubject("인스타 클론코딩 회원가입 인증번호입니다.");
        simpleMailMessage.setText(verifyCode);

        javaMailSender.send(simpleMailMessage);
    }

    private String createVerifyCode() {
        return UUID.randomUUID().toString().substring(0, 5);
    }

    public void verify(final VerifyEmail verifyEmail) {
        final EmailAuth emailAuth = emailAuthRepository.findByEmailAndVerifyCodeAndExpiredFalseAndExpiredDateAfter(
                verifyEmail.getEmail(), verifyEmail.getVerifyCode(), LocalDateTime.now())
            .orElseThrow(() -> new InvalidValueException(ErrorCode.VERIFY_CODE_EXPIRED));

        emailAuth.expired();
    }
}
