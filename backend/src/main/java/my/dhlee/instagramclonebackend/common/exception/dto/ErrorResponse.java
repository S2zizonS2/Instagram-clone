package my.dhlee.instagramclonebackend.common.exception.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final LocalDateTime timestamp;
    public final String code;
    private final String message;
    private final List<Errors> errors;

    public ErrorResponse(final String code, final String message, final List<Errors> errors) {
        this.timestamp = LocalDateTime.now();
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public ErrorResponse(final String code, final String message) {
        this.timestamp = LocalDateTime.now();
        this.code = code;
        this.message = message;
        this.errors = new ArrayList<>();
    }
}
