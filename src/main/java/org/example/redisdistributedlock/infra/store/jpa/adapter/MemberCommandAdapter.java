package org.example.redisdistributedlock.infra.store.jpa.adapter;

import org.example.redisdistributedlock.application.out.MemberCommandPort;
import org.example.redisdistributedlock.domain.auth.Member;
import org.example.redisdistributedlock.infra.store.jpa.entity.MemberEntity;
import org.example.redisdistributedlock.infra.store.jpa.entity.MemberRepository;


public class MemberCommandAdapter implements MemberCommandPort {
    private final MemberRepository memberRepository;

    public MemberCommandAdapter(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member persist(Member member) {
        MemberEntity entity = new MemberEntity(member.getAuth().getId());
        memberRepository.save(entity);
        return Member.builder()
            .id(entity.getId())
            .build();
    }
}
