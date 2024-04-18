package org.example.redisdistributedlock.domain.auth;

import org.example.redisdistributedlock.domain.point.PointHistory;
import org.example.redisdistributedlock.domain.transaction.TransactionHistory;

import java.util.List;

public interface Tradeable {
    List<PointHistory> getPointHistories();
    List<TransactionHistory> getTransactionHistories();
}
