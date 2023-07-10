package com.firesuits.server.domain.article.entity;

import com.firesuits.server.domain.member.entity.Member;
import com.firesuits.server.global.audit.AuditingFields;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "comment_like")
@Entity
public class CommentLike extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_like_id", updatable = false)
    private Long CommentLikeId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "article_comment_id")
    private ArticleComment articleComment;

    @Column(name = "value")
    private int value;

    public static CommentLike of(Member member, ArticleComment articleComment, int value){
        CommentLike entity = new CommentLike();
        entity.setMember(member);
        entity.setArticleComment(articleComment);
        entity.setValue(value);
        return entity;
    }
}
