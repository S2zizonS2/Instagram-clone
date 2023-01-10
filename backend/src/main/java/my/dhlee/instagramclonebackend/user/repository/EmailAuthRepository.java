package my.dhlee.instagramclonebackend.user.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import my.dhlee.instagramclonebackend.user.domain.EmailAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailAuthRepository extends JpaRepository<EmailAuth, Long> {
    Optional<EmailAuth> findByEmailAndVerifyCodeAndExpiredFalseAndExpiredDateAfter(String email, String verifyCode, LocalDateTime now);
}
