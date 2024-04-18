package org.example.redisdistributedlock.infra.store.jpa.adapter;

import org.example.redisdistributedlock.application.out.PointCommandPort;
import org.example.redisdistributedlock.domain.point.PointHistory;
import org.example.redisdistributedlock.infra.store.jpa.entity.PointHistoryEntity;
import org.example.redisdistributedlock.infra.store.jpa.entity.PointHistoryRepository;
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
        PointHistoryEntity entity = new PointHistoryEntity(
            pointHistory.getId(),
            pointHistory.getOwner().getId(),
            pointHistory.getTranId(),
            pointHistory.getPoint().getAmount(),
            pointHistory.getCreateAt()
        );
        pointHistoryRepository.save(entity);
    }

    @Override
    @Transactional
    public void persistAll(List<PointHistory> pointHistories) {
        List<PointHistoryEntity> entities = pointHistories.stream().map(
            it ->
                new PointHistoryEntity(
                    it.getId(),
                    it.getOwner().getId(),
                    it.getTranId(),
                    it.getPoint().getAmount(),
                    it.getCreateAt()
                )
        ).toList();
        pointHistoryRepository.saveAll(entities);

    }
}
