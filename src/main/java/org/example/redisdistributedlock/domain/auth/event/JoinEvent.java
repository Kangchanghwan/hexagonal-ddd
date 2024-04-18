package org.example.redisdistributedlock.domain.auth.event;

import lombok.Getter;
import org.example.redisdistributedlock.config.event.Event;
import org.example.redisdistributedlock.domain.auth.Member;

@Getter
public class JoinEvent extends Event {
    private final Member member;

    public JoinEvent(Member member) {
        this.member = member;
    }
}
