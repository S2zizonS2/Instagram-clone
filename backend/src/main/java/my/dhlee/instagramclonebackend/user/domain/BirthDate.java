package my.dhlee.instagramclonebackend.user.domain;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PROTECTED)
@Getter
@Access(AccessType.FIELD)
@Embeddable
public class BirthDate {
    private int year;
    private int month;
    private int day;
    public BirthDate(final int year, final int month, final int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
