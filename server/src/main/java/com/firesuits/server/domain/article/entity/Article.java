package com.firesuits.server.domain.article.entity;


import com.firesuits.server.domain.member.entity.Member;
import com.firesuits.server.global.audit.AuditingFields;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "article")
@Entity
public class Article extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id", updatable = false)
    private Long articleId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", length = 20000)
    private String content;

    @Column(name = "comment_count")
    private int commentCount;

    @Column(name = "view_count")
    private int viewCount;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<ArticleComment> articleComments = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<View> views;

    public static Article of(String title, String content, Member member){
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setMember(member);
        return article;
    }

    public void addComment(ArticleComment articleComment){
        this.commentCount++;
    }

    public void removeComment(ArticleComment articleComment){
        this.articleComments.remove(articleComment);
        this.commentCount = this.articleComments.size();
    }

    public void incrementViewCount(){
        this.viewCount++;
    }
}
