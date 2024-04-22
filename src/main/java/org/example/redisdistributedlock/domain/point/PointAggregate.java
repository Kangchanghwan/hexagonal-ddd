package org.example.redisdistributedlock.domain.point;

import lombok.Getter;
import org.example.redisdistributedlock.domain.auth.TradeableInfo;

@Getter
public class PointAggregate {

    private final PointHistory history;

    public PointAggregate(PointHistory history) {
        this.history = history;
    }

    public static PointHistory init(TradeableInfo member){
        return PointHistory.newLog(member, Point.ZERO, null);
    }

    public Point getBalance() {
        return history.getPoint();
    }

    public PointHistory pointUp(Point amount, Long tranId) {
        Point resultPoint = this.getBalance().plus(amount);
        return PointHistory.newLog(history.getOwner(), resultPoint, tranId);
    }

    public void pointDownValidation(Point amount) {
        if (amount.isLessThan(Point.ZERO)) {
            throw new IllegalArgumentException("Invalid amount");
        }
        if (this.getBalance().isLessThan(amount)) {
            throw new IllegalArgumentException("Insufficient points");
        }
    }

    public PointHistory pointDown(Point amount, Long tranId) {
        Point reminingPoint = this.getBalance().minus(amount);
        return PointHistory.newLog(history.getOwner(), reminingPoint, tranId);
    }
}
