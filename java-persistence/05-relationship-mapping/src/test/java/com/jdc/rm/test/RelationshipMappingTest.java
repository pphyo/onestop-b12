package com.jdc.rm.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.rm.entity.Account;
import com.jdc.rm.entity.AccountType;
import com.jdc.rm.entity.Budget;
import com.jdc.rm.entity.Budget.BudgetPeriod;
import com.jdc.rm.entity.Category;
import com.jdc.rm.entity.Category.CategoryType;
import com.jdc.rm.entity.Transaction;
import com.jdc.rm.entity.Transaction.TransactionType;

@TestMethodOrder(OrderAnnotation.class)
public final class RelationshipMappingTest extends BaseTest {
	
	@Test
	@Order(2)
	void test_for_remove_and_orphan_removal() {
		
		em.getTransaction().begin();
		
		var accountType = em.find(AccountType.class, 1L);

		var account = accountType.getAccount();
		
		account.getBudgets().remove(0);
		
		em.getTransaction().commit();
		
	}
	
	@Test
	@Order(1)
	void test_for_cascade_persist() {
		
		AccountType main = new AccountType();
		main.setName("Main");
		
		AccountType cash = new AccountType();
		cash.setName("Cash");
		
		Account mainAccount = new Account();
		mainAccount.setName("Main Account");
		mainAccount.setAmount(new BigDecimal(1_000_000));
		
		Account cashAccount = new Account();
		cashAccount.setName("Cash Account");
		cashAccount.setAmount(new BigDecimal(200_000));
		
		mainAccount.addAccountType(main); // bi directional
		cashAccount.addAccountType(cash);
		
		Budget budget1 = new Budget();
		budget1.setAmount(new BigDecimal(30000));
		budget1.setPeriod(BudgetPeriod.Monthly);
		
		mainAccount.addBudget(budget1);
		
		Budget budget2 = new Budget();
		budget2.setAmount(new BigDecimal(10000));
		budget2.setPeriod(BudgetPeriod.Weekly);
		
		mainAccount.addBudget(budget2);
		
		Budget budget3 = new Budget();
		budget3.setAmount(new BigDecimal(50000));
		budget3.setPeriod(BudgetPeriod.Monthly);
		
		cashAccount.addBudget(budget3);
		
		em.getTransaction().begin();
		
		em.persist(main);
		em.persist(cash);
		
		em.getTransaction().commit();
	}
	
	@Test
	@Order(2)
	@Disabled
	void test_for_inserting_data_to_many_to_one() {
		var tx = em.getTransaction();
		
		Category health = new Category();
		health.setName("Health");
		health.setType(CategoryType.Debit);
		
		Category salary = new Category();
		salary.setName("Salary");
		salary.setType(CategoryType.Credit);
		
		tx.begin();
		
		var transaction = new Transaction();
		transaction.setCategories(List.of(health)); // many to many
		transaction.setAmount(new BigDecimal(10000));
		transaction.setType(TransactionType.Expense);
		
		var account = em.find(Account.class, 1);
		transaction.setAccount(account);
		
		em.persist(health);
		em.persist(salary);
		
		em.persist(transaction);
		
		tx.commit();
		
	}
	
	@Test
	@Order(1)
	@Disabled
	void test_for_primary_key_join_column() {
			var tx = em.getTransaction();
			
			tx.begin();

			AccountType main = new AccountType();
			main.setName("Main");
			
			AccountType cash = new AccountType();
			cash.setName("Cash");
			
			AccountType wave = new AccountType();
			wave.setName("Wave Pay");
			
			AccountType creditCard = new AccountType();
			creditCard.setName("Credit Card");
			
			em.persist(main);
			em.persist(cash);
			em.persist(wave);
			em.persist(creditCard);

			Account mainAccount = new Account();
			mainAccount.setName("Main Account");
			mainAccount.setAccountType(main);
			mainAccount.setAmount(new BigDecimal(1_000_000));
			
			Account cashAccount = new Account();
			cashAccount.setName("Cash Account");
			cashAccount.setAccountType(cash);
			cashAccount.setAmount(new BigDecimal(200_000));
			
			em.persist(mainAccount);
			em.persist(cashAccount);
			
			tx.commit();
			
	}

}