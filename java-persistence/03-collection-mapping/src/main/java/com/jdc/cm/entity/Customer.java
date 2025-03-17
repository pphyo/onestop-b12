package com.jdc.cm.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(nullable = false, length = 120)
	private String name;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@ElementCollection
	@CollectionTable(name = "phones", joinColumns = @JoinColumn(name = "customer_id"))
	@Column(name = "phone", nullable = false, unique = true, length = 15)
	private List<String> phones;

	@ElementCollection
	@CollectionTable(name = "emails", joinColumns = @JoinColumn(name = "customer_id"))
	@Column(name = "email", nullable = false, unique = true, length = 100)
	private Set<String> emails;

	@ElementCollection
	@CollectionTable(name = "months", joinColumns = @JoinColumn(name = "customer_id"))
	@Column(name = "name")
	@Enumerated(EnumType.STRING)
	private List<ShoppingMonth> months;

	@ElementCollection
	private List<Item> specials;

//	@ElementCollection
//	@CollectionTable(name = "addresses", joinColumns = @JoinColumn(name = "customer_id"))
//	@MapKeyColumn(name = "address_type")
//	@Column(name = "address", nullable = false, length = 500)
//	private Map<String, String> addresses;
	
	@ElementCollection
	@CollectionTable(name = "addresses", joinColumns = @JoinColumn(name = "customer_id"))
	@MapKeyColumn(name = "address_type")
	@MapKeyEnumerated(EnumType.STRING)
	@Column(name = "address", nullable = false, length = 500)
	private Map<AddressType, String> addresses;
	
	public enum AddressType {
		Permanent, Temporary, Work, Spouse
	}

	public enum Gender {
		Male, Female, Other
	}

	public enum ShoppingMonth {
		Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec
	}
}
