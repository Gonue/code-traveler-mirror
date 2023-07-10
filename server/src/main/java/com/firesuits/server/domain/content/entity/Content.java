package com.firesuits.server.domain.content.entity;

import com.firesuits.server.domain.learn.entity.Learn;
import com.firesuits.server.domain.learn.entity.LearnCheck;
import com.firesuits.server.domain.member.entity.Member;
import com.firesuits.server.domain.quiz.entity.Quiz;
import com.firesuits.server.global.audit.AuditingFields;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "content")
@Entity
public class Content extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id", updatable = false)
    private Long contentId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content_img")
    private String contentImg;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    private List<Learn> learns = new ArrayList<>();

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    private List<Quiz> quizzes = new ArrayList<>();

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    private List<ContentProgress> contentProgresses = new ArrayList<>();

    public static Content of(String title, String contentImg, Member member){
        Content content =new Content();
        content.setTitle(title);
        content.setContentImg(contentImg);
        content.setMember(member);
        return content;
    }
}
