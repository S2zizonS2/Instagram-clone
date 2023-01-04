package my.dhlee.instagramclonebackend.user.domain;

import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.dhlee.instagramclonebackend.user.domain.follow.Follow;

@NoArgsConstructor(access = PROTECTED)
@Getter
@Table(name = "Users")
@Entity
public class User {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String phone;

    @Enumerated(STRING)
    private Gender gender;

    @OneToMany(mappedBy = "follower")
    private final List<Follow> followers = new ArrayList<>();

    @OneToMany(mappedBy = "following")
    private final List<Follow> followings = new ArrayList<>();

    @Builder
    public User(final String userId, final String password, final String name) {
        this.userId = userId;
        this.password = password;
        this.name = name;
    }

    public void follow(User targetUser) {
        final Follow follow = new Follow(this, targetUser);
        this.followings.add(follow);
        targetUser.followers.add(follow);
    }

    public void unfollow(User targetUser) {
        final Follow follow = new Follow(this, targetUser);
        this.followings.remove(follow);
        targetUser.followers.remove(follow);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
