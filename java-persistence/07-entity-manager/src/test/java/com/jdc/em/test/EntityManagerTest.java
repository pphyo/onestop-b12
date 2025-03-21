package com.jdc.em.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.jdc.em.entity.Hero;
import com.jdc.em.entity.Hero.HeroRole;
import com.jdc.em.entity.Skin;

public final class EntityManagerTest extends BaseTest {
	
	@Test
	void test() {
		em.getTransaction().begin();
		
		var hero = new Hero(); // transient or new state
		hero.setName("Franco");
		hero.setSkill(4);
		hero.setRole(HeroRole.Tank);
		hero.setDifficulty(7);
		
		var skin1 = new Skin();
		skin1.setName("Blazing Axe");
		skin1.setPrice(899);
		hero.addSkin(skin1);
		
		var skin2 = new Skin();
		skin2.setName("Masterchef");
		skin2.setPrice(699);
		hero.addSkin(skin2);
		
		assertFalse(em.contains(hero)); // check entity in managed state
		
		em.persist(hero); // managed state
		
		assertTrue(em.contains(hero)); // check entity in managed state
		
		em.detach(hero); // detached state
		
		assertFalse(em.contains(hero)); // check entity in managed state
		
		var mergedHero = em.merge(hero); // managed state
		
		assertTrue(em.contains(mergedHero)); // check entity in managed state
		
		mergedHero.setName("Johnson");
		
		em.getTransaction().commit();
	}

}







