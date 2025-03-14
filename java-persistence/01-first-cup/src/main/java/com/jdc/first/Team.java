package com.jdc.first;

import java.time.Year;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Team {

	@Id
	private int id;
	private String name;
	private int count;
	private Year foundedYear;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Year getFoundedYear() {
		return foundedYear;
	}

	public void setFoundedYear(Year foundedYear) {
		this.foundedYear = foundedYear;
	}

}
