package org.example.redisdistributedlock.infra.store.jpa.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.redisdistributedlock.domain.transaction.TransactionHistory;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name ="tb_transaction_history")
@Getter
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_id")
    private Long from;
    @Column(name = "to_id")
    private Long to;

    private Long amount;

    @Enumerated(EnumType.STRING)
    private TransactionHistory.Type type;

    @Enumerated(EnumType.STRING)
    private TransactionHistory.Status status;

    private LocalDateTime createAt;

    private TransactionEntity(Long id) {
        this.id = id;
    }

    public static TransactionEntity of(Long id){
        return new TransactionEntity(id);
    }

    @Builder
    public TransactionEntity(Long id, Long from, Long to, Long amount, TransactionHistory.Type type, TransactionHistory.Status status, LocalDateTime createAt) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.createAt = createAt;
    }
}