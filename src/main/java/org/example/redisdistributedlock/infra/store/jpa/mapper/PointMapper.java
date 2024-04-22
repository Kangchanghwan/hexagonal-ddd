package org.example.redisdistributedlock.infra.store.jpa.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.redisdistributedlock.domain.auth.TradeableInfo;
import org.example.redisdistributedlock.domain.point.Point;
import org.example.redisdistributedlock.domain.point.PointHistory;
import org.example.redisdistributedlock.infra.store.jpa.entity.PointHistoryEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PointMapper {


    public static PointHistory mapToDomain(PointHistoryEntity entity) {
        return PointHistory.builder()
            .id(entity.getId())
            .point(Point.valueOf(entity.getBalance()))
            .createAt(entity.getCreateAt())
            .owner(TradeableInfo.of(entity.getOwnerId()))
            .tranId(entity.getTranId())
            .build();
    }
    public static PointHistoryEntity maptToEntity(PointHistory pointHistory) {
        return new PointHistoryEntity(
            pointHistory.getId(),
            pointHistory.getOwner().getId(),
            pointHistory.getTranId(),
            pointHistory.getPoint().getAmount(),
            pointHistory.getCreateAt()
        );
    }
}
