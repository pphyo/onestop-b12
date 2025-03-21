package com.jdc.im.entity;

import java.io.Serializable;

import com.jdc.im.converter.StudentNameConverter;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Convert(converter = StudentNameConverter.class)
	@Column(nullable = false, length = 100)
	private StudentName name;

	@Column(nullable = false)
	private Gender gender;

	@AttributeOverride(name = "city", column = @Column(name = "primary_city"))
	@AttributeOverride(name = "township", column = @Column(name = "primary_township"))
	private Address primary;

	@AttributeOverride(name = "city", column = @Column(name = "current_city"))
	@AttributeOverride(name = "township", column = @Column(name = "current_township"))
	private Address current;

	public enum Gender {
		Male, Female
	}

}
