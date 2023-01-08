package my.dhlee.instagramclonebackend.user.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.dhlee.instagramclonebackend.user.dto.VerifyEmail;
import my.dhlee.instagramclonebackend.user.dto.request.AttemptRequest;
import my.dhlee.instagramclonebackend.user.dto.request.BirthDateDto;
import my.dhlee.instagramclonebackend.user.dto.request.SignUpRequest;
import my.dhlee.instagramclonebackend.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
@RestController
public class SignUpController {
    private final UserService userService;

    @PostMapping("/attempt")
    public ResponseEntity<Void> attempt(@Valid @RequestBody AttemptRequest attemptRequest) {
        attemptRequest.validate();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/check_age_eligibility")
    public ResponseEntity<Void> checkAge(@Valid @RequestBody BirthDateDto birthDateDto) {
        birthDateDto.validate();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send_verify_email")
    public ResponseEntity<Void> sendEmail(@Valid @RequestBody VerifyEmail verifyEmail) {

        return ResponseEntity.ok().build();
    }

    @PostMapping("/check_confirmation_code")
    public ResponseEntity<Void> checkConfirmCode(@Valid @RequestBody VerifyEmail verifyEmail) {

        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        signUpRequest.validate();
        userService.signUp(signUpRequest);
        return ResponseEntity.ok().build();
    }
}
