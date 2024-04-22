package org.example.redisdistributedlock.application.usecase;

import jakarta.transaction.Transactional;
import org.example.redisdistributedlock.application.in.TransactionUseCase;
import org.example.redisdistributedlock.application.out.MemberQueryPort;
import org.example.redisdistributedlock.application.out.TransactionCommandPort;
import org.example.redisdistributedlock.application.out.TransactionQueryPort;
import org.example.redisdistributedlock.config.event.Events;
import org.example.redisdistributedlock.domain.auth.Member;
import org.example.redisdistributedlock.domain.transaction.TransactionAggregate;
import org.example.redisdistributedlock.domain.transaction.TransactionHistory;
import org.example.redisdistributedlock.domain.point.event.WireReadyEvent;
import org.springframework.stereotype.Component;

@Component
public class TransactionService implements TransactionUseCase {


    private final TransactionCommandPort transactionCommandPort;
    private final MemberQueryPort memberQueryPort;
    private final TransactionQueryPort transactionQueryPort;

    public TransactionService(TransactionCommandPort transactionCommandPort, MemberQueryPort memberQueryPort, TransactionQueryPort transactionQueryPort) {
        this.transactionCommandPort = transactionCommandPort;
        this.memberQueryPort = memberQueryPort;
        this.transactionQueryPort = transactionQueryPort;
    }

    @Override
    @Transactional
    public void wire(Long from, Long to, Long amount) {
        Member fromMember = memberQueryPort.getMember(from);
        Member toMember = memberQueryPort.getMember(to);

        TransactionAggregate tranAggregate =
            new TransactionAggregate(
                fromMember.getTradeableInfo(),
                toMember.getTradeableInfo(),
                amount,
                TransactionHistory.Type.WIRE
            );
        TransactionAggregate saved = transactionCommandPort.persist(tranAggregate);
        Events.raise(new WireReadyEvent(saved));
    }

    @Transactional
    public void wireFailed(Long tranId) {
        TransactionAggregate transaction = transactionQueryPort.getTransaction(tranId);
        transaction.fail();
        transactionCommandPort.persist(transaction);
    }

    @Transactional
    public void wireSuccess(Long tranId) {
        TransactionAggregate transaction = transactionQueryPort.getTransaction(tranId);
        transaction.success();
        transactionCommandPort.persist(transaction);
    }
}
