package my.dhlee.instagramclonebackend.user.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import my.dhlee.instagramclonebackend.user.domain.Gender;
import my.dhlee.instagramclonebackend.user.domain.User;

public class UserRequest {

    @NotBlank(message = "아이디를 입력해주세요.")
    private final String userId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private final String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private final String name;

    @Pattern(regexp = "^MALE|FEMALE$", message = "올바른 성별을 선택해주세요.")
    @NotBlank(message = "성별을 선택해주세요.")
    private final String gender;

    public User toEntity() {
        return new User(userId, password, name, Gender.valueOf(gender));
    }

    public UserRequest(final String userId, final String password, final String name, final String gender) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.gender = gender;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }
}
