package my.dhlee.instagramclonebackend.user.dto.request;

public class PasswordUpdateRequest {

    private final String oldPassword;
    private final String newPassword;
    private final String newPassword2;

    public PasswordUpdateRequest(final String oldPassword, final String newPassword, final String newPassword2) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPassword2 = newPassword2;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getNewPassword2() {
        return newPassword2;
    }
}
