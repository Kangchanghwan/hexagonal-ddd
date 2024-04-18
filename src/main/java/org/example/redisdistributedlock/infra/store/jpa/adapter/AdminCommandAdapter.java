package org.example.redisdistributedlock.infra.store.jpa.adapter;

import org.example.redisdistributedlock.application.out.AdminCommandPort;
import org.example.redisdistributedlock.domain.auth.Admin;
import org.example.redisdistributedlock.infra.store.jpa.entity.AdminEntity;
import org.example.redisdistributedlock.infra.store.jpa.entity.AdminRepository;

public class AdminCommandAdapter implements AdminCommandPort {

    private final AdminRepository adminRepository;

    public AdminCommandAdapter(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void persist(Admin admin) {
        AdminEntity entity = new AdminEntity(admin.getAuth().getId());
        adminRepository.save(entity);
    }
}
