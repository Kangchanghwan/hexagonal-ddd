package org.example.redisdistributedlock.infra.store.jpa.entity;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    default TransactionEntity findByIdOrThrow(Long id){
        return findById(id).orElseThrow(() -> new EntityNotFoundException("해당하는 거래내역을 찾을 수 없습니다."));
    }
}
