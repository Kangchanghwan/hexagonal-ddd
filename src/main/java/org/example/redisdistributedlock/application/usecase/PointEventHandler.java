package org.example.redisdistributedlock.application.usecase;

import org.example.redisdistributedlock.domain.auth.event.JoinEvent;
import org.example.redisdistributedlock.domain.point.Point;
import org.example.redisdistributedlock.domain.point.event.WireReadyEvent;
import org.example.redisdistributedlock.domain.transaction.TransactionHistory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PointEventHandler {

    private final PointService pointService;

    public PointEventHandler(PointService pointService) {
        this.pointService = pointService;
    }

    @EventListener
    public void JoinEventHandler(JoinEvent joinEvent) {
        pointService.init(joinEvent.getMember().getTradeableInfo());
    }
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void wireReadyEventHandle(WireReadyEvent wireReadyEvent) {
        TransactionHistory transactionHistory = wireReadyEvent.getTransactionAggregate().getTransactionHistory();
        pointService.wire(
            transactionHistory.getFrom(),
            transactionHistory.getTo(),
            Point.valueOf(transactionHistory.getAmount()),
            transactionHistory.getId()
        );
    }
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void wireReadyValidateEventHandle(WireReadyEvent wireReadyEvent) {
        TransactionHistory transactionHistory = wireReadyEvent.getTransactionAggregate().getTransactionHistory();

        pointService.pointDownValidate(
            transactionHistory.getFrom(),
            Point.valueOf(transactionHistory.getAmount())
            );
    }

}
