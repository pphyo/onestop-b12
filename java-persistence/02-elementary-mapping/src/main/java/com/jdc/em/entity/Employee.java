package com.jdc.em.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "employees")
@Entity()
public final class Employee {
	
	@Id
	int id;
	Byte[] byteVal;
	Short[] shortVal;
	Integer[] intVal;
	Long[] longVal;
	Float[] floatVal;
	Double[] doubleVal;
	Character[] charVal;
	Boolean[] booleanVal;
	String[] stringVal;

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
