package org.example.redisdistributedlock.domain.auth;

import lombok.Builder;
import lombok.Getter;
import org.example.redisdistributedlock.domain.point.*;
import org.example.redisdistributedlock.domain.transaction.TransactionHistory;

import java.util.List;

@Getter
public class Member {
    private Long id;
    private TradeableInfo tradeableInfo;
    private DefaultInfo defaultInfo;

    @Builder
    public Member(Long id, List<PointHistory> pointHistories, List<TransactionHistory> transactionHistories, String name) {
        this.id = id;
        this.tradeableInfo =
            new TradeableInfo(id, pointHistories, transactionHistories);
        this.defaultInfo =
            new DefaultInfo(name);
    }

    private Member(String name) {
        this.defaultInfo = new DefaultInfo(name);
    }

    public static Member createMember(String name) {
        return new Member(name);
    }

    private Member(Long id) {
        this.id = id;
    }

    public static Member of(Long id) {
        return new Member(id);
    }

}
