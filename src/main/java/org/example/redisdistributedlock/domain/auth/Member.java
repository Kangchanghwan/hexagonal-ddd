package org.example.redisdistributedlock.domain.auth;

import lombok.Builder;
import lombok.Getter;
import org.example.redisdistributedlock.domain.point.*;
import org.example.redisdistributedlock.domain.transaction.TransactionHistory;

import java.util.List;

@Getter
public class Member {
    private TradeableAuth auth;

    @Builder
    public Member(Long id, List<PointHistory> pointHistories, List<TransactionHistory> transactionHistories) {
        this.auth =
            new TradeableAuth(id, pointHistories, transactionHistories);
    }

    public Member() {
        this.auth = new TradeableAuth();
    }

    private Member(Long id) {
        this.auth = TradeableAuth.of(id);
    }

    public static Member of(Long id) {
        return new Member(id);
    }

}
