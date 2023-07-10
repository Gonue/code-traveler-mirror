package com.firesuits.server.domain.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "attendance")
@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id", updatable = false)
    private Long attendanceId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "check_date")
    private LocalDate  checkDate;
}
