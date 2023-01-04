package my.dhlee.instagramclonebackend.user.domain.follow;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import my.dhlee.instagramclonebackend.user.domain.User;

@Table(uniqueConstraints = {
    @UniqueConstraint(name = "uniqueFollow", columnNames = {"follower_id", "following_id"})
})
@Entity
public class Follow {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "following_id")
    private User following;

    public Follow(final User follower, final User following) {
        if (follower.equals(following)) {
            throw new IllegalArgumentException("뒤질래?");
        }
        this.follower = follower;
        this.following = following;
    }
}
