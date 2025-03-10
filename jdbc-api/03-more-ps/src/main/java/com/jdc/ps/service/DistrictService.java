package com.jdc.ps.service;

import static com.jdc.ps.util.ConnectionManager.getConnection;
import static com.jdc.ps.util.SQLQueries.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdc.ps.entity.District;
import com.jdc.ps.entity.Region;
import com.jdc.ps.entity.State;

public class DistrictService implements BaseService<District> {

	@Override
	public int insert(District entity) {
		
		try(var conn = getConnection();
				var stmt = conn.prepareStatement(DISTRICT_INSERT, Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, entity.getName());
			stmt.setString(2, entity.getBurmese());
			stmt.setInt(3, entity.getState().getId());
			
			stmt.executeUpdate();
			
			var rs = stmt.getGeneratedKeys();
			while(rs.next())
				return rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int insert(List<District> entities) {
		return 0;
	}

	@Override
	public void update(int id, District entity) {
	}

	@Override
	public void delete(int id) {
		
	}

	@Override
	public List<District> selectAll() {
		var result = new ArrayList<District>();
		try(var conn = getConnection();
				var stmt = conn.prepareStatement(DISTRICT_SELECT_ALL)) {
			
			var rs = stmt.executeQuery();
			while(rs.next())
				result.add(getDistrictEntity(rs));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public District selectById(int id) {
		
		try(var conn = getConnection();
				var stmt = conn.prepareStatement(DISTRICT_SELECT_BY_ID)) {
			
			stmt.setInt(1, id);
			
			var rs = stmt.executeQuery();
			while(rs.next())
				return getDistrictEntity(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public long count() {
		return selectAll().size();
	}
	
	private District getDistrictEntity(ResultSet rs) throws SQLException {
		var district = new District();
		district.setId(rs.getInt("district_id"));
		district.setName(rs.getString("district_name"));
		district.setBurmese(rs.getString("district_burmese"));
		district.setDeleted(rs.getBoolean("district_deleted"));
		
		var state = new State();
		state.setId(rs.getInt("state_id"));
		state.setName(rs.getString("state_name"));
		state.setBurmese(rs.getString("state_burmese"));
		state.setRegion(Region.valueOf(rs.getString("region")));
		state.setCapital(rs.getString("capital"));
		state.setPopulation(rs.getInt("population"));
		state.setDeleted(rs.getBoolean("state_deleted"));
		
		district.setState(state);
		return district;
	}

}
