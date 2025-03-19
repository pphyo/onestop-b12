package com.jdc.im.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "posts")
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

//	@Id
//	@SequenceGenerator(name = "posts_SEQ", initialValue = 1, allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_SEQ")
//	private Long id;
//
//	@Id
//	private ZonedDateTime issuedDate;
	
	@EmbeddedId
	private PostPK id = new PostPK();

	@Column(columnDefinition = "TEXT")
	private String status;

	@Transient
	private String image;

	@Column(nullable = false)
	private PrivacySetting privacy;

	public enum PrivacySetting {
		Everyone, OnlyMe, Friends
	}

}
