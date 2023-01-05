package my.dhlee.instagramclonebackend.user.domain;

import static javax.persistence.CascadeType.ALL;
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
import my.dhlee.instagramclonebackend.user.dto.request.PasswordUpdateRequest;
import my.dhlee.instagramclonebackend.user.dto.request.UserEditRequest;

@NoArgsConstructor(access = PROTECTED)
@Getter
@Table(name = "Users")
@Entity
public class User {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "user_id", length = 50, nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(STRING)
    private Gender gender;

    @OneToMany(mappedBy = "follower", cascade = ALL, orphanRemoval = true)
    private final List<Follow> followers = new ArrayList<>();

    @OneToMany(mappedBy = "following", cascade = ALL, orphanRemoval = true)
    private final List<Follow> followings = new ArrayList<>();

    @Builder
    public User(final String userId, final String password, final String name, final Gender gender) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.gender = gender;
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

    public void update(final UserEditRequest userEditRequest) {
        this.userId = userEditRequest.getUserId();
        this.name = userEditRequest.getName();
        this.gender = Gender.valueOf(userEditRequest.getGender());
    }
    public void updatePassword(final PasswordUpdateRequest passwordUpdateRequest) {
        this.password = passwordUpdateRequest.getNewPassword();
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
