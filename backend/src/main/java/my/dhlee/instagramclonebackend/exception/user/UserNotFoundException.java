package my.dhlee.instagramclonebackend.exception.user;

import my.dhlee.instagramclonebackend.exception.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {

    private final static String MESSAGE = "사용자를 찾을 수 없습니다.";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
