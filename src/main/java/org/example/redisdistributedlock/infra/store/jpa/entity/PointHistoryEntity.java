package org.example.redisdistributedlock.infra.store.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name ="tb_point_history")
@Getter
public class PointHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ownerId;

    private Long tranId;

    private BigDecimal balance;

    private LocalDateTime createAt;


    @Builder
    public PointHistoryEntity(Long id, Long ownerId, Long tranId, BigDecimal balance, LocalDateTime createAt) {
        this.id = id;
        this.ownerId = ownerId;
        this.tranId = tranId;
        this.balance = balance;
        this.createAt = createAt;
    }
}
