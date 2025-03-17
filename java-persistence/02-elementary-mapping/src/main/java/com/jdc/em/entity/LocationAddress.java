package com.jdc.em.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class LocationAddress {

	private String address;
	private String township;

	@Column(nullable = false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTownship() {
		return township;
	}

	public void setTownship(String township) {
		this.township = township;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, township);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocationAddress other = (LocationAddress) obj;
		return Objects.equals(address, other.address) && Objects.equals(township, other.township);
	}

}
