package com.jdc.im.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class EnrollmentPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "student_id")
	private Long studentId;

	@Column(name = "course_id")
	private Integer courseId;

	@Column(name = "enroll_at")
	private LocalDateTime enrollAt;

}
