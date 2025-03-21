package com.jdc.im.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class SpaceShip extends Vehicle {
	
	@Column(name= "shield_strength")
	private int shieldStrength;
	
	@Column(name= "weapon_power")
	private int weaponPower;

}
