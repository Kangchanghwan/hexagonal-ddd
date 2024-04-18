package org.example.redisdistributedlock.application.out;

import org.example.redisdistributedlock.domain.transaction.TransactionAggregate;

public interface TransactionQueryPort {
    TransactionAggregate getTransaction(Long id);
}
