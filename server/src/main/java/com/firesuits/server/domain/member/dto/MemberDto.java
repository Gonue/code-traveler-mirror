package com.firesuits.server.domain.member.dto;

import com.firesuits.server.domain.member.entity.Member;
import com.firesuits.server.domain.member.entity.MemberMbti;
import com.firesuits.server.domain.member.entity.MemberTheme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long memberId;
    private String email;
    private String nickName;
    private String password;
    private int experience;
    private int level;
    private int requiredExperience;
    private String profileImage;
    private MemberMbti memberMbti;
    private MemberTheme memberTheme;
    private List<String> roles;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public static MemberDto from(Member entity){
        return new MemberDto(
                entity.getMemberId(),
                entity.getEmail(),
                entity.getNickName(),
                entity.getPassword(),
                entity.getExperience(),
                entity.getLevel(),
                entity.getRequiredExperience(),
                entity.getProfileImage(),
                entity.getMemberMbti(),
                entity.getMemberTheme(),
                entity.getRoles(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }
}
