package com.jdc.test;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.jdc.im.entity.Post;
import com.jdc.im.entity.Post.PrivacySetting;
import com.jdc.im.entity.User;

import jakarta.persistence.Persistence;

public class IdMappingTest {
	
	@Test
	//@Disabled
	void testForMySql() {
		try(var emf = Persistence.createEntityManagerFactory("im-mysql");
				var em = emf.createEntityManager()) {
			
			var post1 = new Post();
			post1.setIssuedDate(ZonedDateTime.now());
			post1.setStatus("Myanmar Java Developer");
			post1.setPrivacy(PrivacySetting.Everyone);
			
			var post2 = new Post();
			post2.setIssuedDate(ZonedDateTime.now());
			post2.setStatus("Myanmar Spring Developer");
			post2.setPrivacy(PrivacySetting.Friends);
			
			var user1 = new User();
			user1.setUsername("pphyo");
			user1.setEmail("pphyo.dev@gmail.com");
			user1.setDob(LocalDate.of(2004, 07, 11));
			
			var user2 = new User();
			user2.setUsername("uminkhant");
			user2.setEmail("uminkhanthu@gmail.com");
			user2.setDob(LocalDate.of(2000, 10, 01));
			
			em.getTransaction().begin();
			
			em.persist(post1);
			
			em.persist(user1);
			
			em.persist(post2);
			
			em.persist(user2);
			
			em.getTransaction().commit();
			
		}
	}

	@Test
	@Disabled
	void testForPostgresql() {
		try(var emf = Persistence.createEntityManagerFactory("im-postgresql")) {}
	}
	
}
