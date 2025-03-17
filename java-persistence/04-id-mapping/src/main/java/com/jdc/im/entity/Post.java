package com.jdc.im.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "posts")
@SequenceGenerator(name = "posts_SEQ")
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_SEQ")
	private int id;

	private ZonedDateTime issuedDate;

	@Column(columnDefinition = "TEXT")
	private String status;

	private String image;

	@Column(nullable = false)
	private PrivacySetting privacy;

	public enum PrivacySetting {
		Everyone, OnlyMe, Friends
	}

}
