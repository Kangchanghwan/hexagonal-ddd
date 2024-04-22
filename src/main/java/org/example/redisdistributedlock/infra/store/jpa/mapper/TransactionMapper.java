package org.example.redisdistributedlock.infra.store.jpa.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.redisdistributedlock.domain.auth.TradeableInfo;
import org.example.redisdistributedlock.domain.transaction.TransactionHistory;
import org.example.redisdistributedlock.infra.store.jpa.entity.TransactionEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionMapper {
    public static TransactionHistory mapToDomain(TransactionEntity entity) {
        TransactionHistory transactionHistory =
            TransactionHistory.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .status(entity.getStatus())
                .type(entity.getType())
                .from(TradeableInfo.of(entity.getFrom()))
                .to(TradeableInfo.of(entity.getTo()))
                .createAt(entity.getCreateAt())
                .build();
        return transactionHistory;
    }
    public static TransactionEntity mapToEntity(TransactionHistory transactionHistory) {
        return new TransactionEntity(
            transactionHistory.getId(),
            transactionHistory.getFrom().getId(),
            transactionHistory.getTo().getId(),
            transactionHistory.getAmount(),
            transactionHistory.getType(),
            transactionHistory.getStatus(),
            transactionHistory.getCreateAt()
        );
    }
}
