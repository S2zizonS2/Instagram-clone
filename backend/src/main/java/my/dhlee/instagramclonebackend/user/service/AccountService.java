package my.dhlee.instagramclonebackend.user.service;

import lombok.RequiredArgsConstructor;
import my.dhlee.instagramclonebackend.common.exception.ErrorCode;
import my.dhlee.instagramclonebackend.common.exception.InvalidValueException;
import my.dhlee.instagramclonebackend.user.domain.User;
import my.dhlee.instagramclonebackend.user.dto.request.AttemptRequest;
import my.dhlee.instagramclonebackend.user.dto.request.SignUpRequest;
import my.dhlee.instagramclonebackend.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AccountService {

    private final UserRepository userRepository;
    public void validateUserId(final AttemptRequest attemptRequest) {
        final String userId = attemptRequest.getUserId();
        if (userRepository.findByUserId(userId).isPresent()) {
            throw new InvalidValueException(ErrorCode.DUPLICATE_USER_ID);
        }
    }


    public void signUp(final SignUpRequest signUpRequest) {
        User user = signUpRequest.toEntity();
        userRepository.save(user);
    }
}
