package com.jdc.ps.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdc.ps.entity.Region;
import com.jdc.ps.entity.State;

import static com.jdc.ps.util.SQLQueries.*;
import static com.jdc.ps.util.ConnectionManager.getConnection;

public class StateService implements BaseService<State> {
	
	private final String ENTITY_NAME = "states";
	
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
		
		try(var conn = getConnection();
				var stmt = conn.prepareStatement(STATE_INSERT)) {
			
			for(State entity : entities) {
				
				stmt.setString(1, entity.getName());
				stmt.setString(2, entity.getBurmese());
				stmt.setString(3, entity.getRegion().name());
				stmt.setString(4, entity.getCapital());
				stmt.setInt(5, entity.getPopulation());
				
				stmt.addBatch();
			}
			
			return stmt.executeBatch().length;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
				var stmt = conn.prepareStatement(deleteStatement(ENTITY_NAME))) {
			
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<State> selectAll() {
		List<State> result = new ArrayList<>();
		
		try(var conn = getConnection();
				var stmt = conn.createStatement()) {
			
			var rs = stmt.executeQuery(selectAllStatement(ENTITY_NAME));
			
			while(rs.next()) {
				result.add(getStateEntity(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public State selectById(int id) {
		
		try(var conn = getConnection();
				var stmt = conn.prepareStatement(selectByIdStatement(ENTITY_NAME))) {
			
			stmt.setInt(1, id);
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				return getStateEntity(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public long count() {
		
		try(var conn = getConnection();
				var stmt = conn.createStatement()) {
			
			var rs = stmt.executeQuery(countStatement(ENTITY_NAME));
			while(rs.next()) {
				return rs.getLong(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	private State getStateEntity(ResultSet rs) throws SQLException {
		var state = new State();

		state.setId(rs.getInt("id"));
		state.setName(rs.getString("name"));
		state.setBurmese(rs.getString("burmese"));
		state.setRegion(Region.valueOf(rs.getString("region")));
		state.setCapital(rs.getString("capital"));
		state.setPopulation(rs.getInt("population"));
		state.setDeleted(rs.getBoolean("deleted"));
		return state;
	}

}
