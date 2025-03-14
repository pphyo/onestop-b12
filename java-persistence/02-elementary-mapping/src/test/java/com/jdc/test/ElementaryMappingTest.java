package com.jdc.test;

import org.junit.jupiter.api.Test;

import jakarta.persistence.Persistence;

public class ElementaryMappingTest {
	
	@Test
	void test() {
		Persistence.createEntityManagerFactory("em").close();
	}

}
