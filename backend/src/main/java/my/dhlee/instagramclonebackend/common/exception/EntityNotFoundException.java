package my.dhlee.instagramclonebackend.common.exception;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
