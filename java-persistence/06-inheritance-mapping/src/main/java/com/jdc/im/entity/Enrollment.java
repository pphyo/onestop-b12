package com.jdc.im.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "enrollments")
public class Enrollment implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EnrollmentPK id;

	@Column(name = "enrollment_fee", nullable = false)
	private double enrollmentFee;

	@ManyToOne
	@MapsId("courseId")
	private Course course;

	@ManyToOne
	@MapsId("studentId")
	private Student student;

}
