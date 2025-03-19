package com.jdc.rm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "issued_date", nullable = false)
	private LocalDateTime issuedDate = LocalDateTime.now();

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal amount;

	@Column(nullable = false)
	private TransactionType type;

	@ElementCollection
	@CollectionTable(name = "tags", joinColumns = @JoinColumn(name = "transaction_id"))
	@Column(name = "name", length = 150)
	private List<String> tags = new ArrayList<>();

	private String comment;
	
	@ManyToMany
	@JoinTable(
		name = "transactions_categories",
		joinColumns = @JoinColumn(name = "transaction_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private List<Category> categories;

	@ManyToOne(optional = false)
	@JoinColumn(name = "account_id", referencedColumnName = "account_type_id")
	private Account account;

	public enum TransactionType {
		Income, Expense
	}

}
