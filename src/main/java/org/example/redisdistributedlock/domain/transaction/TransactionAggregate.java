package org.example.redisdistributedlock.domain.transaction;

import lombok.Getter;
import org.example.redisdistributedlock.domain.auth.TradeableInfo;

@Getter
public class TransactionAggregate {

    private final TransactionHistory transactionHistory;

    public TransactionAggregate(TradeableInfo from, TradeableInfo to, Long amount, TransactionHistory.Type type) {
        this.transactionHistory = TransactionHistory.newLog(
            from, to, amount, type
        );
    }

    public TransactionAggregate(TransactionHistory transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public void success() {
        transactionHistory.success();
    }
    public void fail() {
        transactionHistory.fail();
    }

}
