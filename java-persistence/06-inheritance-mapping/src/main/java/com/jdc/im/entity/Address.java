package com.jdc.im.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	private String city;
	private String township;

}
