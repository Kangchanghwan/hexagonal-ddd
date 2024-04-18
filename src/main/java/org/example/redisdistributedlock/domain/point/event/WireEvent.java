package org.example.redisdistributedlock.domain.point.event;

import lombok.Getter;

@Getter
public class WireEvent {
    private final Long tranId;

    public WireEvent(Long tranId) {
        this.tranId = tranId;
    }
}
