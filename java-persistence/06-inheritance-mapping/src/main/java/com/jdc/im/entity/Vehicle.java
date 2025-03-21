package com.jdc.im.entity;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import lombok.Data;

@Data
@Entity
@Inheritance
@DiscriminatorColumn(
	name = "vehicle_type",
	discriminatorType = DiscriminatorType.STRING,
	length = 20
)
public class Vehicle {
	
	@Id
	private Integer id;

	private String manufacturer;

}
