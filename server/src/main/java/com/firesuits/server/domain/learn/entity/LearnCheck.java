package com.firesuits.server.domain.learn.entity;

import com.firesuits.server.domain.content.entity.ContentProgress;
import com.firesuits.server.domain.member.entity.Member;
import com.firesuits.server.global.audit.AuditingFields;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "learn_check")
@Entity
public class LearnCheck extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "learn_check_id", updatable = false)
    private Long learnCheckId;

    @Column(name = "completed")
    private Boolean completed;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "learn_id")
    private Learn learn;
    @ManyToOne
    @JoinColumn(name = "content_progress_id")
    private ContentProgress contentProgress;

    public static LearnCheck of(Learn learn, Member member, ContentProgress contentProgress){
        LearnCheck learnCheck = new LearnCheck();
        learnCheck.setMember(member);
        learnCheck.setLearn(learn);
        learnCheck.setCompleted(false);
        learnCheck.setContentProgress(contentProgress);
        return learnCheck;
    }
}
