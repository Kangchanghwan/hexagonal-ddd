package org.example.redisdistributedlock.infra.store.jpa.adapter;

import org.example.redisdistributedlock.application.out.MemberQueryPort;
import org.example.redisdistributedlock.domain.auth.Member;
import org.example.redisdistributedlock.infra.store.jpa.entity.MemberEntity;
import org.example.redisdistributedlock.infra.store.jpa.entity.MemberRepository;

public class MemberQueryAdapter implements MemberQueryPort {
    private final MemberRepository memberRepository;

    public MemberQueryAdapter(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member getMember(Long id) {
        MemberEntity memberEntity = memberRepository.findByIdOrThrow(id);
        return Member.builder()
            .id(memberEntity.getId())
            .transactionHistories(null)
            .pointHistories(null)
            .build();
    }
}
