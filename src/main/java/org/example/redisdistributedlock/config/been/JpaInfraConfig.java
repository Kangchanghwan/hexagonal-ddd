package org.example.redisdistributedlock.config.been;

import org.example.redisdistributedlock.application.out.*;
import org.example.redisdistributedlock.infra.store.jpa.adapter.*;
import org.example.redisdistributedlock.infra.store.jpa.entity.AdminRepository;
import org.example.redisdistributedlock.infra.store.jpa.entity.MemberRepository;
import org.example.redisdistributedlock.infra.store.jpa.entity.PointHistoryRepository;
import org.example.redisdistributedlock.infra.store.jpa.entity.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JpaInfraConfig {

    @Bean
    AdminCommandPort adminCommandPort(AdminRepository adminRepository) {
        return new AdminCommandAdapter(adminRepository);
    }
    @Bean
    AdminQueryPort adminQueryPort(
        AdminRepository adminRepository
    ) {
        return new AdminQueryAdapter(adminRepository);
    }
    @Bean
    MemberCommandPort memberCommandPort(MemberRepository memberRepository){
        return new MemberCommandAdapter(memberRepository);
    }
    @Bean
    MemberQueryPort memberQueryPort(MemberRepository memberRepository){
        return new MemberQueryAdapter(memberRepository);
    }
    @Bean
    TransactionCommandPort transactionLogCommandPort(TransactionRepository transactionRepository){
        return new TransactionCommandAdapter(transactionRepository);
    }
    @Bean
    PointCommandPort pointCommandPort(PointHistoryRepository pointHistoryRepository){
        return new PointCommandAdapter(pointHistoryRepository);
    }
    @Bean
    TransactionQueryPort transactionQueryPort(TransactionRepository transactionRepository){
        return new TransactionQueryAdapter(transactionRepository);
    }
    @Bean
    PointQueryPort pointQueryPort(PointHistoryRepository pointHistoryRepository) {
        return new PointQueryAdapter(pointHistoryRepository);
    }
}
