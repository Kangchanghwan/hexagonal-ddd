package org.example.redisdistributedlock.application.out;

import org.example.redisdistributedlock.domain.transaction.TransactionAggregate;
import org.example.redisdistributedlock.domain.transaction.TransactionHistory;

public interface TransactionCommandPort {
    TransactionAggregate persist(TransactionAggregate transaction);
}
