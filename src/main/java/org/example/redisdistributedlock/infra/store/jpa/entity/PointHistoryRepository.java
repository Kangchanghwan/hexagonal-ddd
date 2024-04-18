package org.example.redisdistributedlock.infra.store.jpa.entity;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PointHistoryRepository extends JpaRepository<PointHistoryEntity, Long> {

    Collection<PointHistoryEntity> findByOwnerIdOrderById(Long ownerId);
}
