package com.jdc.em.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

@Table(
	name = "employees", 
	uniqueConstraints = @UniqueConstraint(columnNames = "username")
)
@Entity()
@SecondaryTable(
	name = "contact",
	uniqueConstraints = {
		@UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "phone")
	}
)
public final class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	int id;
	
	@Column(nullable = false, unique = true, length = 100)
	String username;
	
	@Column(nullable = false, length = 100, table = "contact")
	String email;
	
	@Column(
		columnDefinition = "varchar(15) not null check(phone regexp '09\\w{9}')",
		table = "contact"
	)
	String phone;
	
	@Column(name = "big_int_val", precision = 10)
	BigInteger bigIntVal;
	
	@Column(name = "big_dec_val", precision = 10, scale = 2)
	BigDecimal bigDecVal;
	
	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	EmployeeType type;
	
	@Temporal(TemporalType.DATE)
	java.util.Date utilDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	java.util.Calendar utilCalendar;
	
	java.sql.Date sqlDate;	
	java.sql.Time sqlTime;
	java.sql.Timestamp sqlTimeStamp;
	
	@Column(name = "local_date")
	java.time.LocalDate localDate;

	@Column(name = "local_time")
	java.time.LocalTime localTime;
	
	@Column(name = "local_datetime")
	java.time.LocalDateTime localDateTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	java.time.OffsetDateTime offsetDateTime;
	java.time.ZonedDateTime zonedDateTime;

	public enum EmployeeType {
		Daily, Monthly, Contract
	}

//	@Entity
//	public class Department {
//		
//		private int id;
//		private String name;
//
//		public int getId() {
//			return id;
//		}
//
//		public void setId(int id) {
//			this.id = id;
//		}
//
//		public String getName() {
//			return name;
//		}
//
//		public void setName(String name) {
//			this.name = name;
//		}
//
//	}

}
