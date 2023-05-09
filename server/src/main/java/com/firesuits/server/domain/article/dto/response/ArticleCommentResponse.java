package com.firesuits.server.domain.article.dto.response;

import com.firesuits.server.domain.article.dto.ArticleCommentDto;
import com.firesuits.server.domain.member.dto.response.MemberResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ArticleCommentResponse {
    private Long articleCommentId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private MemberResponse member;

    public static ArticleCommentResponse from(ArticleCommentDto dto){
        return new ArticleCommentResponse(
                dto.getArticleCommentId(),
                dto.getContent(),
                dto.getCreatedAt(),
                dto.getModifiedAt(),
                MemberResponse.from(dto.getMember())
        );
    }
}