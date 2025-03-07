package com.jdc.ps.test;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@TestMethodOrder(OrderAnnotation.class)
public class PreparedStatmentTest {
	// url, user, password
	static final String URL = "jdbc:mysql://localhost:3306/jdbc_db";
	static final String USR = "onestop";
	static final String PWD = "onestop";
	
	@ParameterizedTest
	@Order(5)
	@ValueSource(ints = {})
	void test_for_delete(int id) {
		
	}
	
	@ParameterizedTest
	@Order(4)
	@ValueSource(ints = {})
	void test_for_select_by_id(int id) {
		
	}
	
	@Test
	@Order(3)
	void test_for_select_all() {
		
	}
	
	@ParameterizedTest
	@Order(2)
	@CsvSource("Jurgen Klopp, Germany, 16, 1")
	void test_for_update(String name, String nationality, int expYear, int id) {
		
		final String UPDATE = """
				UPDATE coaches set name = ?, nationality = ?,
				experience_year = ? where coach_id = ?
				""";
		
		try(var conn = DriverManager.getConnection(URL, USR, PWD);
				var stmt = conn.prepareStatement(UPDATE)) {
			
			stmt.setString(1, name);
			stmt.setString(2, nationality);
			stmt.setInt(3, expYear);
			stmt.setInt(4, id);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@ParameterizedTest
	@Order(1)
	@CsvSource({
		"Mikel Arteta, Spain, 5",
		"Carlo Ancelotti, Italy, 15",
		"Arne Slot, Netherland, 12",
		"Luis Enrique, Spain, 10"
	})
	void test_for_insert(String name, String nationality, int expYear) {
		
		final String INSERT = """
				INSERT coaches (name, nationality, experience_year) values
				(?, ?, ?)
				""";
		
		try(var conn = DriverManager.getConnection(URL, USR, PWD);
				var stmt = conn.prepareStatement(INSERT)) {
			
			stmt.setString(1, name);
			stmt.setString(2, nationality);
			stmt.setInt(3, expYear);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		final String DROP = "drop table if exists coaches";
		final String CREATE = """
				CREATE TABLE coaches(
					coach_id int primary key auto_increment,
					name varchar(255) not null,
					nationality varchar(100) not null,
					experience_year tinyint not null
				)
				""";
		
		try(var conn = DriverManager.getConnection(URL, USR, PWD);
				var stmt = conn.createStatement()) {
			
			stmt.execute(DROP);
			stmt.execute(CREATE);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}