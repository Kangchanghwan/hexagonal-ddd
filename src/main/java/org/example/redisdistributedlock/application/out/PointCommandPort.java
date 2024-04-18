package org.example.redisdistributedlock.application.out;

import org.example.redisdistributedlock.domain.point.PointHistory;

import java.util.List;

public interface PointCommandPort {
    void persist(PointHistory pointHistory);
    void persistAll(List<PointHistory> pointHistories);
}
