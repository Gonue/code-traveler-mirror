package com.firesuits.server.domain.quiz.entity;

import com.firesuits.server.domain.content.entity.Content;
import com.firesuits.server.domain.member.entity.Member;
import com.firesuits.server.global.audit.AuditingFields;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "quiz")
@Entity
public class Quiz extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id", updatable = false)
    private Long quizId;

    @Column(name = "detail")
    private String detail;

    @Column(name = "example")
    private String example;

    @Column(name = "commentary")
    private String commentary;

    @Column(name = "correct")
    private boolean correct;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @OneToOne(mappedBy = "quiz")
    private QuizResult quizResult;

    public static Quiz of(Content content, Member member, String detail, String example, boolean correct, String commentary){
        Quiz quiz = new Quiz();
        quiz.setContent(content);
        quiz.setMember(member);
        quiz.setDetail(detail);
        quiz.setExample(example);
        quiz.setCommentary(commentary);
        quiz.setCorrect(correct);
        quiz.setContent(content);

        return quiz;
    }
}
