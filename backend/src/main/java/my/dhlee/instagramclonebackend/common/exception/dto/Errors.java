package my.dhlee.instagramclonebackend.common.exception.dto;

import lombok.Getter;

@Getter
public class Errors {

    private final String field;
    private final String message;

    public Errors(final String field, final String message) {
        this.field = field;
        this.message = message;
    }
}
