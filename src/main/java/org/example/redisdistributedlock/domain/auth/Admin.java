package org.example.redisdistributedlock.domain.auth;

import lombok.Builder;
import lombok.Getter;
import org.example.redisdistributedlock.domain.point.PointHistory;
import org.example.redisdistributedlock.domain.transaction.TransactionHistory;

import java.util.List;

@Getter
public class Admin  {
    private final Long id;
    private final TradeableInfo tradeableInfo;
    private DefaultInfo defaultInfo;


    @Builder
    public Admin(
        Long id,
        List<PointHistory> pointHistories,
        List<TransactionHistory> transactionHistories,
        String name) {
        this.id = id;
        this.tradeableInfo =
            new TradeableInfo(id, pointHistories, transactionHistories);
        this.defaultInfo =
            new DefaultInfo(name);
    }
    private Admin(Long id){
        this.id = id;
        this.tradeableInfo = TradeableInfo.of(id);
        this.defaultInfo = DefaultInfo.of(id);
    }
    public static Admin of(Long id){
        return new Admin(id);
    }

}
