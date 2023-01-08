package my.dhlee.instagramclonebackend.user.repository;

import my.dhlee.instagramclonebackend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
