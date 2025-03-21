package com.jdc.em.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "heroes")
public class Hero implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "hero", cascade = CascadeType.PERSIST)
	private List<Skin> skins = new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String name;
	
	private int skill;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private HeroRole role;
	
	private int difficulty;
	
	public void addSkin(Skin skin) {
		this.skins.add(skin);
		skin.setHero(this);
	}

	public enum HeroRole {
		Fighter, Marksman, Mage, Support, Tank, Assassin
	}

}
