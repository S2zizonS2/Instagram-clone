package my.dhlee.instagramclonebackend.user.dto.response;

import java.util.List;

public class UserResponses {

    private final List<UserResponse> userResponses;

    public UserResponses(final List<UserResponse> userResponses) {
        this.userResponses = userResponses;
    }
}
