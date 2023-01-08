package my.dhlee.instagramclonebackend.user.dto.request;

import java.time.DateTimeException;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.dhlee.instagramclonebackend.common.exception.ErrorCode;
import my.dhlee.instagramclonebackend.common.exception.InvalidValueException;

@NoArgsConstructor
@Getter
public class BirthDateDto {
    private int year;
    private int month;
    private int day;

    public BirthDateDto(final int year, final int month, final int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public void validate() {
        isValidYear();
        validateDateFormat();
        isOver14YearsOld();
    }

    private void isValidYear() {
        if (year < 1900 || 2099 < year) {
            throw new InvalidValueException(ErrorCode.INVALID_BIRTH_DATE);
        }
    }

    private LocalDate validateDateFormat() {
        try {
            return LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new InvalidValueException(ErrorCode.INVALID_BIRTH_DATE);
        }
    }

    private void isOver14YearsOld() {
        final LocalDate now = LocalDate.now();

        final int targetYear = year + 14;
        final LocalDate over14YearsDate = LocalDate.of(targetYear, month, day);

        if (now.isBefore(over14YearsDate)) {
            throw new InvalidValueException(ErrorCode.NOT_ELIGIBILITY_AGE);
        }
    }

}
