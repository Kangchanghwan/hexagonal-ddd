package org.example.redisdistributedlock.domain.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.redisdistributedlock.domain.point.PointHistory;
import org.example.redisdistributedlock.domain.transaction.TransactionHistory;

import java.util.List;

@NoArgsConstructor
public class Admin {

    @Getter
    private TradeableAuth auth;


    @Builder
    public Admin(Long id, List<PointHistory> pointHistories, List<TransactionHistory> transactionHistories) {
        this.auth =
            new TradeableAuth(id, pointHistories, transactionHistories);
    }
    private Admin(Long id){
        this.auth = TradeableAuth.of(id);
    }
    public static Admin of(Long id){
        return new Admin(id);
    }

}
