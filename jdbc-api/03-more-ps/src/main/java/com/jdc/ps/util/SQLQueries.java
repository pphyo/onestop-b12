package com.jdc.ps.util;

public interface SQLQueries {

	String STATE_INSERT = "insert states (name, burmese, region, capital, population) values (?, ?, ?, ?, ?)";
	String STATE_UPDATE = "update states set name = ?, burmese = ?, region = ?, capital = ?, population = ? where id = ?";
	
	String STATE_TOTAL_POPULATION_BY_REGION = "select region, count(id) state_count, sum(population) total_population from states group by region having total_population >= ? order by total_population desc";

	String DISTRICT_INSERT = "insert districts (name, burmese, state_id) values (?, ?, ?)";
	String DISTRICT_UPDATE = "update districts set name = ?, burmese = ?, state_id = ? where id = ?";
	String DISTRICT_SELECT_ALL = "select d.id district_id, d.name district_name, d.burmese district_burmese, d.deleted district_deleted, s.id state_id, s.name state_name, s.burmese state_burmese, s.region, s.capital, s.population, s.deleted state_deleted from districts d join states s on d.state_id = s.id";
	String DISTRICT_SELECT_BY_ID = DISTRICT_SELECT_ALL.concat(" where d.id = ?");

	static String deleteStatement(String entityName) {
		return "delete from %s where id = ?".formatted(entityName);
	}

	static String countStatement(String entityName) {
		return "select count(*) from %s".formatted(entityName);
	}

	static String selectAllStatement(String entityName) {
		return "select * from %s".formatted(entityName);
	}

	static String selectByIdStatement(String entityName) {
		return selectAllStatement(entityName).concat(" where id = ?");
	}

	String STATE_TABLE = """
			CREATE TABLE states(
				id int primary key auto_increment,
				name varchar(255) not null,
				burmese varchar(255) not null,
				region enum('East', 'West', 'South', 'North', 'Lower', 'Central') not null,
				capital varchar(100) not null,
				population int not null,
				deleted boolean not null default false
			) character set 'utf8'
			""";

	String DISTRICT_TABLE = """
			CREATE TABLE districts(
				id int primary key auto_increment,
				name varchar(255) not null,
				burmese varchar(255) not null,
				deleted boolean not null default false,
				state_id int not null,
				constraint districts_state_id foreign key(state_id)
				references states(id) on update cascade
			) character set 'utf8'
			""";

	String TOWNSHIP_TABLE = """
			CREATE TABLE townships(
				id int primary key auto_increment,
				name varchar(255) not null,
				burmese varchar(255) not null,
				district_id int not null,
				constraint townships_district_id foreign key(district_id)
				references districts(id) on update cascade
			) character set 'utf8'
			""";
}
