package com.jdc.im.test;

import org.junit.jupiter.api.Test;

import com.jdc.im.entity.Car;

public final class InheritanceMappingTest extends BaseTest {
	
	@Test
	void test() {
		var car = new Car();
		car.setId(1);
		car.setManufacturer("Audi");
		car.setDoor(4);
		
		em.getTransaction().begin();
		em.persist(car);
		em.getTransaction().commit();
	}

}
