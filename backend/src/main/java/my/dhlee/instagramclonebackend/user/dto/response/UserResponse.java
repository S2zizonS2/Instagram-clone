package my.dhlee.instagramclonebackend.user.dto.response;

import my.dhlee.instagramclonebackend.user.domain.User;

public class UserResponse {

    private Long id;
    private String userId;
    private String name;

    public UserResponse() {}

    public UserResponse(final Long id, final String userId, final String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }
    public static UserResponse of(User user) {
        return new UserResponse(user.getId(), user.getUserId(), user.getName());
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
