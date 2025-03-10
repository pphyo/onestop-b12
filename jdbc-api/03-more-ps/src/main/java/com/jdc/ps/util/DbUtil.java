package com.jdc.ps.util;

import static com.jdc.ps.util.ConnectionManager.getConnection;
import static com.jdc.ps.util.SQLQueries.*;

import java.sql.SQLException;;

public class DbUtil {
	
	public static void prepareSchema() {
		try(var conn = getConnection();
				var stmt = conn.createStatement()) {
			
			stmt.execute("drop schema if exists jdbc_db");
			stmt.execute("create schema jdbc_db character set 'utf8'");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createTables() {
		try(var conn = getConnection();
				var stmt = conn.createStatement()) {
			
			stmt.execute(STATE_TABLE);
			stmt.execute(DISTRICT_TABLE);
			stmt.execute(TOWNSHIP_TABLE);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void dropTables(String... tables) {
		try(var conn = getConnection();
				var stmt = conn.createStatement()) {
			
			stmt.execute("set foreign_key_checks = 0");
			
			for(String table : tables) {
				stmt.execute("drop table if exists %s".formatted(table));
			}
			
			stmt.execute("set foreign_key_checks = 1");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
