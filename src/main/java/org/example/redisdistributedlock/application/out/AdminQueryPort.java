package org.example.redisdistributedlock.application.out;

import org.example.redisdistributedlock.domain.auth.Admin;

public interface AdminQueryPort {
    Admin getAdmin(Long id);
}
