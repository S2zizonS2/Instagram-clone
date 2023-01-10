package my.dhlee.instagramclonebackend.user.service;

import lombok.RequiredArgsConstructor;
import my.dhlee.instagramclonebackend.user.domain.User;
import my.dhlee.instagramclonebackend.user.dto.request.UserEditRequest;
import my.dhlee.instagramclonebackend.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;

    public void update(final Long id, final UserEditRequest userEditRequest) {
        final User user = findById(id);
        user.update(userEditRequest);
    }

    public void delete(final Long id) {
        final User user = findById(id);

        userRepository.delete(user);
    }

    private User findById(final Long id) {
        return null;
    }


}
