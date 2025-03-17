package com.jdc.cm.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal price;

	@Column(nullable = false, length = 150)
	private String name;

	@Column(nullable = false)
	private int stock;

}
