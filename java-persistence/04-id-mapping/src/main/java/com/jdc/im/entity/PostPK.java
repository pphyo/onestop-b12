package com.jdc.im.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PostPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private ZonedDateTime issuedAt;

}
