package com.firesuits.server.domain.quiz.entity;

import com.firesuits.server.domain.content.entity.Content;
import com.firesuits.server.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "quiz_result")
@Entity
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_result_id")
    private Long quizResultId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @Column(name = "answer")
    private Boolean answer;

    @Column(name = "result")
    private Boolean result;

    @Column(name = "total_count")
    private int totalCount;

    @Column(name = "correct_count")
    private int correctCount;

    @Column(name = "wrong_count")
    private int wrongCount;

    @Column(name = "check_point")
    private Boolean checkPoint;

    public static QuizResult of(Quiz quiz, Member member, Content content, Boolean answer, Boolean result, int totalCount, int correctCount, int wrongCount, Boolean checkPoint){
        QuizResult quizResult = new QuizResult();
        quizResult.setContent(content);
        quizResult.setQuiz(quiz);
        quizResult.setMember(member);
        quizResult.setAnswer(answer);
        quizResult.setResult(result);
        quizResult.setTotalCount(totalCount);
        quizResult.setCorrectCount(correctCount);
        quizResult.setWrongCount(wrongCount);
        quizResult.setCheckPoint(checkPoint);

        return quizResult;
    }
}
