package org.example.redisdistributedlock.application.out;

import org.example.redisdistributedlock.domain.auth.Admin;

public interface AdminCommandPort {
    void persist(Admin admin);
}
