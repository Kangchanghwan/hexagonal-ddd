package org.example.redisdistributedlock.domain.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.redisdistributedlock.domain.point.PointHistory;
import org.example.redisdistributedlock.domain.transaction.TransactionHistory;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class TradeableInfo implements Tradeable{

    private Long id;

    private List<PointHistory> pointHistories = new ArrayList<>();
    private List<TransactionHistory> transactionHistories = new ArrayList<>();

    private TradeableInfo(Long id) {
        this.id = id;
    }

    public TradeableInfo(Long id, List<PointHistory> pointHistories, List<TransactionHistory> transactionHistories) {
        this.id = id;
        this.pointHistories = pointHistories;
        this.transactionHistories = transactionHistories;
    }

    public static TradeableInfo of(Long id) {
        return new TradeableInfo(id);
    }

}
