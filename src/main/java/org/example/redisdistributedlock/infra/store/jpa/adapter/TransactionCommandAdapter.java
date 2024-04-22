package org.example.redisdistributedlock.infra.store.jpa.adapter;

import jakarta.transaction.Transactional;
import org.example.redisdistributedlock.application.out.TransactionCommandPort;
import org.example.redisdistributedlock.domain.auth.TradeableInfo;
import org.example.redisdistributedlock.domain.transaction.TransactionAggregate;
import org.example.redisdistributedlock.domain.transaction.TransactionHistory;
import org.example.redisdistributedlock.infra.store.jpa.entity.TransactionEntity;
import org.example.redisdistributedlock.infra.store.jpa.entity.TransactionRepository;

public class TransactionCommandAdapter implements TransactionCommandPort {

    private final TransactionRepository transactionRepository;

    public TransactionCommandAdapter(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public TransactionAggregate persist(TransactionAggregate transaction) {
        TransactionHistory transactionHistory = transaction.getTransactionHistory();
        TransactionEntity entity = new TransactionEntity(
            transactionHistory.getId(),
            transactionHistory.getFrom().getId(),
            transactionHistory.getTo().getId(),
            transactionHistory.getAmount(),
            transactionHistory.getType(),
            transactionHistory.getStatus(),
            transactionHistory.getCreateAt()
        );
        TransactionEntity saved = transactionRepository.save(entity);
        TransactionHistory savedHistory = TransactionHistory.builder()
            .id(saved.getId())
            .amount(saved.getAmount())
            .status(saved.getStatus())
            .type(saved.getType())
            .from(TradeableInfo.of(saved.getFrom()))
            .to(TradeableInfo.of(saved.getTo()))
            .createAt(saved.getCreateAt())
            .build();
        return new TransactionAggregate(savedHistory);
    }
}
