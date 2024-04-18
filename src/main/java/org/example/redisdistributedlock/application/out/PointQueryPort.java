package org.example.redisdistributedlock.application.out;

import org.example.redisdistributedlock.domain.point.PointAggregate;

public interface PointQueryPort {
    PointAggregate getPoint(Long ownerId);
}
