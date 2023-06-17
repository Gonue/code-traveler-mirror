package com.firesuits.server.domain.member.repository;

import com.firesuits.server.domain.member.entity.Member;
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

    private final RedisTemplate<String, Member> memberDtoRedisTemplate;
    private final static Duration MEMBER_CACHE_TTL = Duration.ofDays(2);

    public void setMember(Member member){
        String key = getKey(member.getEmail());
        log.info("Set Member to Redis {} : {} ", key, member);
        memberDtoRedisTemplate.opsForValue().set(key, member, MEMBER_CACHE_TTL);
    }

    public Optional<Member> getMember(String email){
        String key = getKey(email);
        Member member = memberDtoRedisTemplate.opsForValue().get(key);
        log.info("Get Member to Redis {} : {} ", key, member);
        return Optional.ofNullable(member);
    }

    private String getKey(String email){
        return "MEMBER:" + email;
    }
}
