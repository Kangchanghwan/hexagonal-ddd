package org.example.redisdistributedlock.infra.store.jpa.entity;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    default AdminEntity findByIdOrThrow(Long id){
        return findById(id).orElseThrow(() -> new EntityNotFoundException("AdminEntity not found with id: " + id));
    }

}
