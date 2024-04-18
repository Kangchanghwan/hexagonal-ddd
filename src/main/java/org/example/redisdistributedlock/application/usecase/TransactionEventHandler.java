package org.example.redisdistributedlock.application.usecase;

import org.example.redisdistributedlock.domain.point.event.WireEvent;
import org.example.redisdistributedlock.domain.point.event.WireReadyEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class TransactionEventHandler {

    private final TransactionService transactionService;

    public TransactionEventHandler(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void wireSucceedEventHandler(WireEvent wireEvent) {
        transactionService.wireSuccess(wireEvent.getTranId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void wireFailedEventHandler(WireEvent wireEvent) {
        transactionService.wireFailed(wireEvent.getTranId());
    }
}
