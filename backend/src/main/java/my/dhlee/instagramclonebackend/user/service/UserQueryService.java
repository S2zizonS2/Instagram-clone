package my.dhlee.instagramclonebackend.user.service;

import java.util.List;
import my.dhlee.instagramclonebackend.exception.user.UserNotFoundException;
import my.dhlee.instagramclonebackend.user.domain.User;
import my.dhlee.instagramclonebackend.user.domain.follow.Follow;
import my.dhlee.instagramclonebackend.user.dto.response.UserResponse;
import my.dhlee.instagramclonebackend.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class UserQueryService {

    private final UserRepository userRepository;

    public UserQueryService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse findUserById(final Long id) {
        final User user = findById(id);
        return UserResponse.of(user);
    }

    public List<Follow> findFollowers(Long id) {
        return userRepository.findFollowersById(id);
    }

    private User findById(final Long id) {
        return userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);
    }
}
