package org.example.redisdistributedlock.infra.store.jpa.adapter;

import org.example.redisdistributedlock.application.out.AdminQueryPort;
import org.example.redisdistributedlock.domain.auth.Admin;
import org.example.redisdistributedlock.infra.store.jpa.entity.AdminEntity;
import org.example.redisdistributedlock.infra.store.jpa.entity.AdminRepository;

public class AdminQueryAdapter implements AdminQueryPort {

    private final AdminRepository adminRepository;

    public AdminQueryAdapter(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin getAdmin(Long id) {
        AdminEntity adminEntity = adminRepository.findByIdOrThrow(id);
        return Admin.builder()
            .id(adminEntity.getId())
            .pointHistories(null)
            .transactionHistories(null)
            .build();
    }
}
