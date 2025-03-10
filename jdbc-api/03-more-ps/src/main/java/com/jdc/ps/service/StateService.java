package com.jdc.ps.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.jdc.ps.entity.State;

import static com.jdc.ps.util.SQLQueries.*;
import static com.jdc.ps.util.ConnectionManager.getConnection;;

public class StateService implements BaseService<State> {
	
	@Override
	public int insert(State entity) {
		
		try(var conn = getConnection();
				var stmt = conn.prepareStatement(STATE_INSERT, Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, entity.getName());
			stmt.setString(2, entity.getBurmese());
			stmt.setString(3, entity.getRegion().name());
			stmt.setString(4, entity.getCapital());
			stmt.setInt(5, entity.getPopulation());
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			while(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int insert(List<State> entities) {
		return 0;
	}

	@Override
	public void update(int id, State entity) {
		try(var conn = getConnection();
				var stmt = conn.prepareStatement(STATE_UPDATE)) {
			
			stmt.setString(1, entity.getName());
			stmt.setString(2, entity.getBurmese());
			stmt.setString(3, entity.getRegion().name());
			stmt.setString(4, entity.getCapital());
			stmt.setInt(5, entity.getPopulation());
			stmt.setInt(6, id);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		
		try(var conn = getConnection();
				var stmt = conn.prepareStatement(STATE_DELETE)) {
			
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<State> selectAll() {
		return null;
	}

	@Override
	public State selectById(int id) {
		return null;
	}

}
