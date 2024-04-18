package org.example.redisdistributedlock.infra.store.jpa.adapter;

import org.example.redisdistributedlock.application.out.TransactionQueryPort;
import org.example.redisdistributedlock.domain.auth.TradeableAuth;
import org.example.redisdistributedlock.domain.transaction.TransactionAggregate;
import org.example.redisdistributedlock.domain.transaction.TransactionHistory;
import org.example.redisdistributedlock.infra.store.jpa.entity.TransactionEntity;
import org.example.redisdistributedlock.infra.store.jpa.entity.TransactionRepository;

public class TransactionQueryAdapter implements TransactionQueryPort {

    private final TransactionRepository transactionRepository;

    public TransactionQueryAdapter(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionAggregate getTransaction(Long id) {
        TransactionEntity entity = transactionRepository.findByIdOrThrow(id);
        TransactionHistory transactionHistory = TransactionHistory.builder()
            .id(entity.getId())
            .amount(entity.getAmount())
            .status(entity.getStatus())
            .type(entity.getType())
            .from(TradeableAuth.of(entity.getFrom()))
            .to(TradeableAuth.of(entity.getTo()))
            .createAt(entity.getCreateAt())
            .build();
        return new TransactionAggregate(transactionHistory);
    }
}
