package com.jdc.im.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Product extends BaseEntity {
	
	@Column(nullable = false)
	private String name;
	private double price;

}
