package com.firesuits.server.domain.member.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "password_reset")
@Entity
public class PasswordReset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "password_reset_id", updatable = false)
    private Long passwordResetId;

    @OneToOne(targetEntity = Member.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "memer_id")
    private Member member;

    @Column(name = "token")
    private String token;

    @Column(name = "expiration")
    private LocalDateTime expiration;
}
