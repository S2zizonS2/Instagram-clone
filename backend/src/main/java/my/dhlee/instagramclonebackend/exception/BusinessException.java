package my.dhlee.instagramclonebackend.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(final String message) {
        super(message);
    }
}
