package org.example.redisdistributedlock.application.usecase;

import org.example.redisdistributedlock.application.in.MemberUseCase;
import org.example.redisdistributedlock.application.out.MemberCommandPort;
import org.example.redisdistributedlock.config.event.Events;
import org.example.redisdistributedlock.domain.auth.Member;
import org.example.redisdistributedlock.domain.auth.event.JoinEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MemberService implements MemberUseCase {

    private final MemberCommandPort memberCommandPort;

    public MemberService(MemberCommandPort memberCommandPort) {
        this.memberCommandPort = memberCommandPort;
    }

    @Override
    @Transactional
    public void join() {
        Member member = new Member();
        member = memberCommandPort.persist(member);
        Events.raise(new JoinEvent(member));
    }
}
