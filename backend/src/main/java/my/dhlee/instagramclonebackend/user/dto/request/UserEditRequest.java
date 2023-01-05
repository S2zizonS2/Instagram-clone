package my.dhlee.instagramclonebackend.user.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserEditRequest {

    @NotBlank(message = "아이디를 입력해주세요.")
    private final String userId;

    @NotBlank(message = "이름을 입력해주세요.")
    private final String name;

    @Pattern(regexp = "^MALE|FEMALE$", message = "올바른 성별을 선택해주세요.")
    @NotBlank(message = "성별을 선택해주세요.")
    private final String gender;

    public UserEditRequest(final String userId, final String name, final String gender) {
        this.userId = userId;
        this.name = name;
        this.gender = gender;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

}
