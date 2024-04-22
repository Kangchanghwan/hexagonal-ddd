package org.example.redisdistributedlock.application.usecase;

import org.example.redisdistributedlock.application.out.PointCommandPort;
import org.example.redisdistributedlock.application.out.PointQueryPort;
import org.example.redisdistributedlock.config.event.Events;
import org.example.redisdistributedlock.domain.auth.TradeableInfo;
import org.example.redisdistributedlock.domain.point.Point;
import org.example.redisdistributedlock.domain.point.PointAggregate;
import org.example.redisdistributedlock.domain.point.PointHistory;
import org.example.redisdistributedlock.domain.point.event.WireEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PointService {

    private final PointCommandPort pointCommandPort;
    private final PointQueryPort pointQueryPort;
    public PointService(PointCommandPort pointCommandPort, PointQueryPort pointQueryPort) {
        this.pointCommandPort = pointCommandPort;
        this.pointQueryPort = pointQueryPort;
    }

    @Transactional
    public void init(TradeableInfo member){
        pointCommandPort.persist(PointAggregate.init(member));
    }
    @Transactional
    public void wire(TradeableInfo from, TradeableInfo to, Point amount, Long tranId){
        PointAggregate fromPoint = pointQueryPort.getPoint(from.getId());
        PointAggregate toPoint = pointQueryPort.getPoint(to.getId());

        PointHistory fromPointHistory = fromPoint.pointDown(amount,tranId);
        PointHistory toPointHistory = toPoint.pointUp(amount,tranId);
        pointCommandPort.persistAll(List.of(fromPointHistory, toPointHistory));
        Events.raise(new WireEvent(tranId));
    }
    public void pointDownValidate(TradeableInfo auth, Point amount){
        PointAggregate fromPointAggregate = pointQueryPort.getPoint(auth.getId());
        fromPointAggregate.pointDownValidation(amount);
    }
}
