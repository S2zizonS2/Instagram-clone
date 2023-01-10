package my.dhlee.instagramclonebackend.user.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.dhlee.instagramclonebackend.user.dto.request.VerifyEmail;
import my.dhlee.instagramclonebackend.user.dto.request.AttemptRequest;
import my.dhlee.instagramclonebackend.user.dto.request.BirthDateDto;
import my.dhlee.instagramclonebackend.user.dto.request.SignUpRequest;
import my.dhlee.instagramclonebackend.user.service.AccountService;
import my.dhlee.instagramclonebackend.user.service.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
@RestController
public class AccountController {
    private final AccountService accountService;
    private final MailService mailService;

    @PostMapping("/attempt")
    public ResponseEntity<Void> attempt(@Valid @RequestBody AttemptRequest attemptRequest) {
        attemptRequest.validate();
        accountService.validateUserId(attemptRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/check_age_eligibility")
    public ResponseEntity<Void> checkAge(@Valid @RequestBody BirthDateDto birthDateDto) {
        birthDateDto.validate();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send_verify_email")
    public ResponseEntity<Void> sendEmail(@Valid @RequestBody VerifyEmail verifyEmail) {
        mailService.sendEmail(verifyEmail);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/check_confirmation_code")
    public ResponseEntity<Void> checkConfirmCode(@Valid @RequestBody VerifyEmail verifyEmail) {
        mailService.verify(verifyEmail);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        signUpRequest.validate();
        accountService.signUp(signUpRequest);
        return ResponseEntity.ok().build();
    }
}
