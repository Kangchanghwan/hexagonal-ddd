package org.example.redisdistributedlock.infra.store.jpa.adapter;

import jakarta.transaction.Transactional;
import org.example.redisdistributedlock.application.out.TransactionCommandPort;
import org.example.redisdistributedlock.domain.auth.TradeableInfo;
import org.example.redisdistributedlock.domain.transaction.TransactionAggregate;
import org.example.redisdistributedlock.domain.transaction.TransactionHistory;
import org.example.redisdistributedlock.infra.store.jpa.entity.TransactionEntity;
import org.example.redisdistributedlock.infra.store.jpa.entity.TransactionRepository;
import org.example.redisdistributedlock.infra.store.jpa.mapper.TransactionMapper;

public class TransactionCommandAdapter implements TransactionCommandPort {

    private final TransactionRepository transactionRepository;

    public TransactionCommandAdapter(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public TransactionAggregate persist(TransactionAggregate transaction) {
        TransactionHistory transactionHistory = transaction.getTransactionHistory();
        TransactionEntity entity = TransactionMapper.mapToEntity(transactionHistory);
        TransactionEntity saved = transactionRepository.save(entity);
        TransactionHistory savedHistory = TransactionMapper.mapToDomain(saved);
        return new TransactionAggregate(savedHistory);
    }


}
