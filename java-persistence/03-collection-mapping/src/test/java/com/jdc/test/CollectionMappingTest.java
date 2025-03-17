package com.jdc.test;

import org.junit.jupiter.api.Test;

import jakarta.persistence.Persistence;

public class CollectionMappingTest {

	@Test
	void test() {
		
		try(var emf = Persistence.createEntityManagerFactory("cm-mysql")) {
			
		}
		
	}
	
}
