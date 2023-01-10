package my.dhlee.instagramclonebackend.user.repository;

import java.util.Optional;
import my.dhlee.instagramclonebackend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);
}
