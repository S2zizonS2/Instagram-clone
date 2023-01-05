package my.dhlee.instagramclonebackend.user.repository;

import java.util.List;
import my.dhlee.instagramclonebackend.user.domain.User;
import my.dhlee.instagramclonebackend.user.domain.follow.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select f from User u join Follow f on u.id = f.following.id where u.id = ?")
    List<Follow> findFollowersById(Long id);

    @Query("select f from User u join Follow f on u.id = f.follower.id where u.id = ?")
    List<Follow> findFollowingsById(Long id);

}
