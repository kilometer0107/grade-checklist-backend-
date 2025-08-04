package com.team.grade_checklist.domain.user.entity;

import com.team.grade_checklist.domain.user.enums.Major;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String name;

    private Integer studentId;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Major major;
}
