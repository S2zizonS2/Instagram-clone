package my.dhlee.instagramclonebackend.user.service;

import static org.junit.jupiter.api.Assertions.*;

import my.dhlee.instagramclonebackend.user.dto.request.VerifyEmail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MailServiceTest {

    @Autowired
    private MailService mailService;

    @DisplayName("메일 전송")
    @Test
    void send() throws Exception {
        final VerifyEmail verifyEmail = new VerifyEmail("1", "dhlee3994@gmail.com");
        assertDoesNotThrow(() -> mailService.sendEmail(verifyEmail));
    }

}