package com.jdc.ps.util;

public interface SQLQueries {
	
	String STATE_INSERT = "insert into states (name, burmese, region, capital, population) values (?, ?, ?, ?, ?)";
	String STATE_UPDATE = "update states set name = ?, burmese = ?, region = ?, capital = ?, population = ? where id = ?";
	String STATE_DELETE = "delete from states where id = ?";
	String STATE_SELECT_ALL = "select * from states";
	String STATE_SELECT_BY_ID = STATE_SELECT_ALL.concat(" where id = ?");

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
	
	static final String DISTRICT_TABLE = """
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










