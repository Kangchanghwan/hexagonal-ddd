package org.example.redisdistributedlock.domain.point;

import lombok.Builder;
import lombok.Getter;
import org.example.redisdistributedlock.domain.auth.TradeableAuth;

import java.time.LocalDateTime;

@Getter
public class PointHistory {

    private Long id;
    private final TradeableAuth owner;
    private final Long tranId;
    private final Point point;
    private final LocalDateTime createAt;


    private PointHistory(TradeableAuth owner, Long tranId, Point point, LocalDateTime createAt) {
        this.owner = owner;
        this.tranId = tranId;
        this.point = point;
        this.createAt = createAt;
    }

    @Builder
    public PointHistory(TradeableAuth owner, Long tranId, Point point, LocalDateTime createAt, Long id) {
        this.owner = owner;
        this.tranId = tranId;
        this.point = point;
        this.createAt = createAt;
        this.id = id;
    }

    public static PointHistory newLog(TradeableAuth owner, Point point, Long tranId) {
        return new PointHistory(owner, tranId, point, LocalDateTime.now());
    }


}
