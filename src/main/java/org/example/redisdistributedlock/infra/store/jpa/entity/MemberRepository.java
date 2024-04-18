package org.example.redisdistributedlock.infra.store.jpa.entity;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    default MemberEntity findByIdOrThrow(Long id){
        return findById(id).orElseThrow(() -> new EntityNotFoundException("AdminEntity not found with id: " + id));
    }
}
