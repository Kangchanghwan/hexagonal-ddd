package org.example.redisdistributedlock.domain.point.event;

import lombok.Getter;
import org.example.redisdistributedlock.domain.transaction.TransactionAggregate;
import org.example.redisdistributedlock.domain.transaction.TransactionHistory;

@Getter
public class WireReadyEvent {
    private final TransactionAggregate transactionAggregate;

    public WireReadyEvent(TransactionAggregate transactionAggregate) {
        this.transactionAggregate = transactionAggregate;
    }
}
