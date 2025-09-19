package com.team.grade_checklist.domain.boxes.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EnrolledSubjectsId implements Serializable {
    private String user;
    private Integer subject;
}
