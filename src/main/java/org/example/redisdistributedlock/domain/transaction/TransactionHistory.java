package org.example.redisdistributedlock.domain.transaction;

import lombok.Builder;
import lombok.Getter;
import org.example.redisdistributedlock.domain.auth.TradeableAuth;
import org.example.redisdistributedlock.domain.point.Point;

import java.time.LocalDateTime;


@Getter
public class TransactionHistory {
    private Long id;
    private final TradeableAuth to;
    private final TradeableAuth from;
    private final Long amount;

    private final Type type;
    private Status status;
    private LocalDateTime createAt;

    public enum Type {
        WIRE, WITHDRAW, CHARGE
    }
    public enum Status {
        READY, SUCCESS, FAIL
    }

    private TransactionHistory(TradeableAuth to, TradeableAuth from, Long amount, Type type, Status status) {
        this.to = to;
        this.from = from;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.createAt = LocalDateTime.now();
    }

    @Builder
    public TransactionHistory(Long id, TradeableAuth to, TradeableAuth from, Long amount, Type type, Status status, LocalDateTime createAt) {
        this.id = id;
        this.to = to;
        this.from = from;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.createAt = createAt;
    }

    public static TransactionHistory newLog(TradeableAuth from, TradeableAuth to, Long amount, Type type) {
        return new TransactionHistory(to, from, amount, type, Status.READY);
    }
    public void success(){
        if(status != Status.READY){
            throw new IllegalArgumentException("변경가능한 상태가 아닙니다.");
        }
        this.status = Status.SUCCESS;
    }
    public void fail() {
        if(status != Status.READY){
            throw new IllegalArgumentException("변경가능한 상태가 아닙니다.");
        }
        this.status = Status.FAIL;
    }
}
