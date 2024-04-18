package org.example.redisdistributedlock.domain.point;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
public class Point {

    private final BigDecimal amount;

    Point(BigDecimal amount) {
        this.amount = amount;
    }

    public static final Point ZERO = Point.valueOf(0L);

    public static Point valueOf(long amount) {
        return new Point(BigDecimal.valueOf(amount));
    }

    public static Point valueOf(BigDecimal amount) {
        return new Point(amount);
    }

    public Point plus(Point amount) {
        return new Point(this.amount.add(amount.amount));
    }

    public Point minus(Point amount) {
        return new Point(this.amount.subtract(amount.amount));
    }

    public Point times(double percent) {
        return new Point(this.amount.multiply(
            BigDecimal.valueOf(percent)
        ));
    }

    public boolean isLessThan(Point other) {
        return amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThanOrEquals(Point other) {
        return amount.compareTo(other.amount) >= 0;
    }
}
