package org.example.redisdistributedlock.application.out;

import org.example.redisdistributedlock.domain.auth.Member;

public interface MemberCommandPort {
    Member persist(Member member);
}
