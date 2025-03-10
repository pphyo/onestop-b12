package com.jdc.ps.entity;

public class District {

	private int id;
	private String name;
	private String burmese;
	private boolean deleted;

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

	public String getBurmese() {
		return burmese;
	}

	public void setBurmese(String burmese) {
		this.burmese = burmese;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
