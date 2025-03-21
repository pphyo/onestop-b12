package com.jdc.im.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public sealed class BaseTest permits InheritanceMappingTest {
	
	static EntityManagerFactory EMF;
	EntityManager em;
	
	@AfterEach
	void tearDown() {
		if(null != em && em.isOpen())
			em.close();
	}
	
	@BeforeEach
	void setUp() {
		em = EMF.createEntityManager();
	}
	
	@AfterAll
	static void tearDownAfterClass() {
		if(EMF != null && EMF.isOpen()) {
			EMF.close();
		}
	}

	@BeforeAll
	static void setUpBeforeClass() {
		EMF = Persistence.createEntityManagerFactory("im");
	}
	
}
