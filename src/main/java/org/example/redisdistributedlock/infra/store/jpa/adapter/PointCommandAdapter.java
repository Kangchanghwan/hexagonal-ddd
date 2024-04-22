package org.example.redisdistributedlock.infra.store.jpa.adapter;

import org.example.redisdistributedlock.application.out.PointCommandPort;
import org.example.redisdistributedlock.domain.point.PointHistory;
import org.example.redisdistributedlock.infra.store.jpa.entity.PointHistoryEntity;
import org.example.redisdistributedlock.infra.store.jpa.entity.PointHistoryRepository;
import org.example.redisdistributedlock.infra.store.jpa.mapper.PointMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PointCommandAdapter implements PointCommandPort {

    private final PointHistoryRepository pointHistoryRepository;

    public PointCommandAdapter(PointHistoryRepository pointHistoryRepository) {
        this.pointHistoryRepository = pointHistoryRepository;
    }

    @Override
    @Transactional
    public void persist(PointHistory pointHistory) {
        PointHistoryEntity entity = PointMapper.maptToEntity(pointHistory);
        pointHistoryRepository.save(entity);
    }



    @Override
    @Transactional
    public void persistAll(List<PointHistory> pointHistories) {
        List<PointHistoryEntity> entities = pointHistories.stream().map(
            PointMapper::maptToEntity
        ).toList();
        pointHistoryRepository.saveAll(entities);

    }
}
