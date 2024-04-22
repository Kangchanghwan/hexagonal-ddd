package org.example.redisdistributedlock.domain.point;

import lombok.Builder;
import lombok.Getter;
import org.example.redisdistributedlock.domain.auth.TradeableInfo;

import java.time.LocalDateTime;

@Getter
public class PointHistory {

    private Long id;
    private final TradeableInfo owner;
    private final Long tranId;
    private final Point point;
    private final LocalDateTime createAt;


    private PointHistory(TradeableInfo owner, Long tranId, Point point, LocalDateTime createAt) {
        this.owner = owner;
        this.tranId = tranId;
        this.point = point;
        this.createAt = createAt;
    }

    @Builder
    public PointHistory(TradeableInfo owner, Long tranId, Point point, LocalDateTime createAt, Long id) {
        this.owner = owner;
        this.tranId = tranId;
        this.point = point;
        this.createAt = createAt;
        this.id = id;
    }

    public static PointHistory newLog(TradeableInfo owner, Point point, Long tranId) {
        return new PointHistory(owner, tranId, point, LocalDateTime.now());
    }


}
