package com.firesuits.server.domain.member.entity;

import com.firesuits.server.domain.article.entity.Article;
import com.firesuits.server.domain.article.entity.ArticleComment;
import com.firesuits.server.domain.article.entity.CommentLike;
import com.firesuits.server.domain.content.entity.Content;
import com.firesuits.server.domain.content.entity.ContentProgress;
import com.firesuits.server.domain.learn.entity.Learn;
import com.firesuits.server.domain.learn.entity.LearnCheck;
import com.firesuits.server.domain.quiz.entity.Quiz;
import com.firesuits.server.domain.quiz.entity.QuizResult;
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
@Table(name = "member")
@Entity
public class Member extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long memberId;
    @Column(name = "email", nullable = false, unique = true, updatable = false, length = 50)
    private String email;
    @Column(name = "nick_name", nullable = false)
    private String nickName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "experience")
    private int experience;
    @Column(name = "level")
    private int level;
    @Column(name = "required_experience")
    private int requiredExperience;
    @Column(name = "profile_image")
    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_mbti", nullable = false)
    private MemberMbti memberMbti;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_theme", nullable = false)
    private MemberTheme memberTheme;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Attendance> attendances = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private PasswordReset passwordReset;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ArticleComment> articleComments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<CommentLike> commentLikes = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Content> contents = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Learn> learns = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Quiz> quizzes = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<QuizResult> quizResults = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<LearnCheck> learnChecks = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ContentProgress> contentProgresses = new ArrayList<>();

    public static Member of(String email, String nickName, String encodedPwd, MemberMbti memberMbti, MemberTheme memberTheme) {
        Member entity = new Member();
        entity.setEmail(email);
        entity.setNickName(nickName);
        entity.setPassword(encodedPwd);
        entity.setMemberMbti(memberMbti);
        entity.setMemberTheme(memberTheme);
        entity.setLevel(0);
        entity.updateRequiredExperience();
        return entity;
    }

    public void addExperience(int exp) {
        this.experience += exp;
        updateRequiredExperience();
        if (this.level < 5) {
            checkLevelUp();
        }
    }

    private void checkLevelUp() {
        while (this.experience >= ((this.level + 1) * 100)) {
            this.experience -= ((this.level + 1) * 100);
            levelUp();
        }
    }

    public void levelUp() {
        if (this.level < 5) {
            this.level++;
        }
        updateRequiredExperience();
    }

    private void updateRequiredExperience() {
        if (this.level == 5) {
            this.requiredExperience = 0;
        } else {
            this.requiredExperience = ((this.level + 1) * 100) - this.experience;
        }
    }
}