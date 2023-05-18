package com.firesuits.server.domain.content.service;

import com.firesuits.server.domain.content.dto.ContentProgressDto;
import com.firesuits.server.domain.content.entity.Content;
import com.firesuits.server.domain.content.entity.ContentProgress;
import com.firesuits.server.domain.content.repository.ContentProgressRepository;
import com.firesuits.server.domain.content.repository.ContentRepository;
import com.firesuits.server.domain.learn.entity.LearnCheck;
import com.firesuits.server.domain.learn.repository.LearnCheckRepository;
import com.firesuits.server.domain.member.entity.Member;
import com.firesuits.server.domain.member.repository.MemberRepository;
import com.firesuits.server.global.error.exception.BusinessLogicException;
import com.firesuits.server.global.error.exception.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContentProgressService {

    private final ContentProgressRepository contentProgressRepository;
    private final LearnCheckRepository learnCheckRepository;
    private final MemberRepository memberRepository;
    private final ContentRepository contentRepository;

    public ContentProgressService(ContentProgressRepository contentProgressRepository, LearnCheckRepository learnCheckRepository, MemberRepository memberRepository, ContentRepository contentRepository) {
        this.contentProgressRepository = contentProgressRepository;
        this.learnCheckRepository = learnCheckRepository;
        this.memberRepository = memberRepository;
        this.contentRepository = contentRepository;
    }

    @Transactional
    public void updateContentProgress(String email, Long contentId) {
        Member member = memberOrException(email);
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CONTENT_NOT_FOUND));
        ContentProgress contentProgress = contentProgressRepository.findByMemberAndContent_ContentId(member, contentId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.WRONG_CODE, String.format("Progress 오류", content)));

        List<LearnCheck> learnCheckList = learnCheckRepository.findAllByMemberAndLearn_ContentBoard_ContentId(member, contentId);
        double completedCount = learnCheckList.stream().filter(LearnCheck::isCompleted).count();
        double progress = completedCount / learnCheckList.size() * 100;

        contentProgress.setProgress(progress);
    }

    public ContentProgressDto getContentProgress(Long contentId, String email){
        Member member = memberOrException(email);
        Content content = contentOrException(contentId);

        ContentProgress contentProgress = contentProgressRepository.findByMemberAndContent_ContentId(member, contentId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.CONTENT_NOT_FOUND));
        return ContentProgressDto.from(contentProgress);
    }

    private Content contentOrException(Long contentId){
        return contentRepository.findById(contentId).orElseThrow(()->
                new BusinessLogicException(ExceptionCode.CONTENT_NOT_FOUND, String.format("%s 번의 컨텐츠가 존재 하지 않습니다.", contentId)));
    }

    private Member memberOrException(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND, String.format("%s 를 찾을 수 없습니다.", email)));
    }
}