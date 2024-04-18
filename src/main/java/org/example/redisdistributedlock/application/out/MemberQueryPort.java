package org.example.redisdistributedlock.application.out;

import org.example.redisdistributedlock.domain.auth.Member;

public interface MemberQueryPort {
    Member getMember(Long id);
}
