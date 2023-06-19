package com.firesuits.server.global.auth.userservice;

import com.firesuits.server.domain.member.dto.MemberDto;
import com.firesuits.server.domain.member.entity.Member;
import com.firesuits.server.domain.member.repository.MemberCacheRepository;
import com.firesuits.server.domain.member.repository.MemberRepository;
import com.firesuits.server.global.auth.utils.CustomAuthorityUtils;
import com.firesuits.server.global.error.exception.BusinessLogicException;
import com.firesuits.server.global.error.exception.ExceptionCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberCacheRepository memberCacheRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<MemberDto> optionalMemberDto = memberCacheRepository.getMember(email);
        if (optionalMemberDto.isPresent()) {
            return new MemberDetails(optionalMemberDto.get());
        }
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        Member findMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        MemberDto memberDto = MemberDto.from(findMember);
        memberCacheRepository.setMember(memberDto);
        return new MemberDetails(memberDto);
    }

    @Getter
    public final class MemberDetails implements UserDetails {
        private MemberDto memberDto;

        MemberDetails(MemberDto memberDto) {
            this.memberDto = memberDto;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(memberDto.getRoles());
        }

        @Override
        public String getPassword() {
            return memberDto.getPassword();
        }

        @Override
        public String getUsername() {
            return memberDto.getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
