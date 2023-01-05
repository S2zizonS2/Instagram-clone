package my.dhlee.instagramclonebackend.exception;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(final String message) {
        super(message);
    }
}
