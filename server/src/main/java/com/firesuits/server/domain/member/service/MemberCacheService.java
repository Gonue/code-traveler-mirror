package com.firesuits.server.domain.member.service;

import com.firesuits.server.domain.member.dto.MemberDto;
import com.firesuits.server.domain.member.repository.MemberCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberCacheService {

    private final MemberCacheRepository memberCacheRepository;

    public void updateMember(MemberDto memberDto) {
        memberCacheRepository.updateMember(memberDto);
    }

    public void deleteMember(String email){
        memberCacheRepository.deleteMember(email);
    }
}
