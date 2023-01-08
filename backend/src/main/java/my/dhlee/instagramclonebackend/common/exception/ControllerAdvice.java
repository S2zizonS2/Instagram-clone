package my.dhlee.instagramclonebackend.common.exception;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import my.dhlee.instagramclonebackend.common.exception.dto.ErrorResponse;
import my.dhlee.instagramclonebackend.common.exception.dto.Errors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        final List<Errors> fieldErrors = e.getBindingResult().getFieldErrors().stream()
            .map(error -> new Errors(error.getField(), error.getDefaultMessage()))
            .collect(Collectors.toList());

        final ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INVALID_VALUE.getCode(), ErrorCode.INVALID_VALUE.getMessage(), fieldErrors);

        return ResponseEntity.status(ErrorCode.INVALID_VALUE.getStatus())
            .body(errorResponse);
    }

    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<ErrorResponse> invalidValueException(InvalidValueException e) {

        final ErrorCode errorCode = e.getErrorCode();

        final ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), errorCode.getMessage());

        return ResponseEntity.status(errorCode.getStatus())
            .body(errorResponse);
    }
}
