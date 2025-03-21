package com.jdc.rm.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity @Table(name = "budgets")
public class Budget {
	
	@ManyToOne
	private Account account;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal amount;
	
	@Column(nullable = false)
	private BudgetPeriod period;
	
	public enum BudgetPeriod {
		Daily, Weekly, Monthly
	}

}
