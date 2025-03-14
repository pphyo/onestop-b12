package com.jdc.test;

import org.junit.jupiter.api.Test;

import jakarta.persistence.Persistence;

public class FirstCupTest {
	
	@Test
	void test() {
		
		var emf = Persistence.createEntityManagerFactory("01-first-cup");
		
		emf.close();
		
	}

}
