package com.jdc.rm.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {

	@OneToMany
	@JoinColumn(name = "account_id", nullable = false)
	private List<Budget> budgets;

	@OneToOne(mappedBy = "account")
//	@PrimaryKeyJoinColumn(name = "account_type_id")
//	@MapsId
	private AccountType accountType;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal amount;

	// bridge method for bi directional mapping
	public void addAccountType(AccountType type) {
		this.setAccountType(type);
		type.setAccount(this);
	}

}
