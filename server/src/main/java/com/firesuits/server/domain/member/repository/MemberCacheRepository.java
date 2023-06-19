package com.firesuits.server.domain.member.repository;

import com.firesuits.server.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberCacheRepository {

    private final RedisTemplate<String, MemberDto> memberDtoRedisTemplate;
    private final static Duration MEMBER_CACHE_TTL = Duration.ofDays(2);

    public void setMember(MemberDto memberDto){
        String key = getKey(memberDto.getEmail());
        log.info("Set Member to Redis {} : {} ", key, memberDto);
        memberDtoRedisTemplate.opsForValue().set(key, memberDto, MEMBER_CACHE_TTL);
    }

    public Optional<MemberDto> getMember(String email){
        String key = getKey(email);
        MemberDto memberDto = memberDtoRedisTemplate.opsForValue().get(key);
        log.info("Get Member to Redis {} : {} ", key, memberDto);
        return Optional.ofNullable(memberDto);
    }

    private String getKey(String email){
        return "MEMBER:" + email;
    }
}
