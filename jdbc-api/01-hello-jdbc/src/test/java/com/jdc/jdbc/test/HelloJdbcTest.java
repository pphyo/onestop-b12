package com.jdc.jdbc.test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class HelloJdbcTest {
	// url, user, password
	static final String URL = "jdbc:mysql://localhost:3306/jdbc_db";
	static final String USR = "onestop";
	static final String PWD = "onestop";
	
	@Test
	@Order(3)
	void test_for_detele() {
		final String DELETE = "delete from players where player_id = 2";
		
		try(var conn = DriverManager.getConnection(URL, USR, PWD);
				var stmt = conn.createStatement()) {
			
			stmt.executeUpdate(DELETE);
			
		} catch(SQLException e) {
			
		}
	}
	
	@Test
	@Order(2)
	void test_for_select() {
		final String SELECT = "select * from players";

		try(var conn = DriverManager.getConnection(URL, USR, PWD);
				var stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SELECT)) {
			
			while(rs.next()) {
				System.out.println("ID: " + rs.getInt(1));
				System.out.println("Name: " + rs.getString(2));
				System.out.println("Birth Date: " + rs.getDate(3));
				System.out.println("Nationality: " + rs.getString(4));
				System.out.println("Position: " + rs.getString(5));
				System.out.println("Jersey Number: " + rs.getInt(6));
				
				System.out.println();
			}
			
		} catch(SQLException e) {
			
		}
	}
	
	@Test
	@Order(1)
	void test_for_insert_records() {
		try(var conn = DriverManager.getConnection(URL, USR, PWD);
				var stmt = conn.createStatement()) {
			
			final String INSERTS = """
					INSERT INTO players (name, birth_date, nationality, position, jersey_number) value
						('Alisson Becker', '1992-10-02', 'Brazil', 'GK', 1),
						('Virgil van Dijk', '1991-07-08', 'Netherland', 'CB', 4),
						('Mohamed Salah', '1992-06-15', 'Egypt', 'RW', 11);
					""";
			
			stmt.execute(INSERTS);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeAll
	static void init() {
		
		// Class.forName(""); register driver class [optional]
		try(var conn = DriverManager.getConnection(URL, USR, PWD);
				var stmt = conn.createStatement()) {
			
			final String TABLE_DROP = "drop table if exists players";
			
			final String TABLE_CREATE = """
					create table if not exists players(
						player_id int primary key auto_increment,
						name varchar(255) not null,
						birth_date date not null,
						nationality varchar(100) not null,
						position char(3) not null,
						jersey_number tinyint unsigned not null
					);
					""";
			
			stmt.execute(TABLE_DROP);
			stmt.execute(TABLE_CREATE);
			
			System.out.println("Table created.");
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}