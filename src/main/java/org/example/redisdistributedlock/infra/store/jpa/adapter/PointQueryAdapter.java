package org.example.redisdistributedlock.infra.store.jpa.adapter;

import org.example.redisdistributedlock.application.out.PointQueryPort;
import org.example.redisdistributedlock.domain.auth.TradeableInfo;
import org.example.redisdistributedlock.domain.point.Point;
import org.example.redisdistributedlock.domain.point.PointAggregate;
import org.example.redisdistributedlock.domain.point.PointHistory;
import org.example.redisdistributedlock.infra.store.jpa.entity.PointHistoryEntity;
import org.example.redisdistributedlock.infra.store.jpa.entity.PointHistoryRepository;
import org.example.redisdistributedlock.infra.store.jpa.mapper.PointMapper;

public class PointQueryAdapter implements PointQueryPort {
    private final PointHistoryRepository pointHistoryRepository;

    public PointQueryAdapter(PointHistoryRepository pointHistoryRepository) {
        this.pointHistoryRepository = pointHistoryRepository;
    }

    @Override
    public PointAggregate getPoint(Long ownerId) {
        PointHistoryEntity entity = pointHistoryRepository
            .findByOwnerIdOrderById(ownerId)
            .stream()
            .toList()
            .getLast();

        PointHistory pointHistory = PointMapper.mapToDomain(entity);
        return new PointAggregate(pointHistory);
    }

}
