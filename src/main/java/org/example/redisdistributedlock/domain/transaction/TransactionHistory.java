package org.example.redisdistributedlock.domain.transaction;

import lombok.Builder;
import lombok.Getter;
import org.example.redisdistributedlock.domain.auth.TradeableInfo;

import java.time.LocalDateTime;


@Getter
public class TransactionHistory {
    private Long id;
    private final TradeableInfo to;
    private final TradeableInfo from;
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

    private TransactionHistory(TradeableInfo to, TradeableInfo from, Long amount, Type type, Status status) {
        this.to = to;
        this.from = from;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.createAt = LocalDateTime.now();
    }

    @Builder
    public TransactionHistory(Long id, TradeableInfo to, TradeableInfo from, Long amount, Type type, Status status, LocalDateTime createAt) {
        this.id = id;
        this.to = to;
        this.from = from;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.createAt = createAt;
    }

    public static TransactionHistory newLog(TradeableInfo from, TradeableInfo to, Long amount, Type type) {
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
