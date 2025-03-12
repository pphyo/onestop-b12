package com.jdc.ps.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.jdc.ps.entity.District;
import com.jdc.ps.entity.Region;
import com.jdc.ps.service.DistrictService;
import com.jdc.ps.service.StateService;

@TestMethodOrder(OrderAnnotation.class)
public class DistrictSerivceTest {
	
	DistrictService districtService = new DistrictService();
	StateService stateService = new StateService();
	
	void test_for_delete() {}
	void test_for_select_by_id() {}
	
	@Order(4)
	@ParameterizedTest
	@CsvSource({
		",a,,0,7",
		",ka,,0,12",
		",,Lower,0,15",
		",,,1000000,75",
		",,Central,1000000,14",
		"m,m,,0,6",
		"m,m,Central,0,5"
	})
	void test_for_select(String districtName, String stateName, Region region, int population, int expectedRecordCount) {
		var resultList = districtService.select(districtName, stateName, region, population);
		assertEquals(expectedRecordCount, resultList.size());
	}
	
	@Order(3)
	@ParameterizedTest
	@CsvSource({
		"Kyonpyaw, ကျုံပျော်, 2",
		"Pyay, ပြည်, 11"
	})
	void test_for_update(String name, String burmese, int idForUpdate) {
		var district = districtService.selectById(idForUpdate);
		assertNotNull(district);
		district.setName(name);
		district.setBurmese(burmese);
		districtService.update(idForUpdate, district);
		
		var updatedDistrict = districtService.selectById(idForUpdate);
		assertNotNull(updatedDistrict);
		assertEquals(name, updatedDistrict.getName());
		assertEquals(burmese, updatedDistrict.getBurmese());
	}
	
	@Order(2)
	@ParameterizedTest
	@MethodSource("getDistrictEntityList")
	void test_for_insert_entities(List<District> entities, int insertedCount) {
		var result = districtService.insert(entities);
		assertEquals(insertedCount, result);
	}
	
	static Stream<Arguments> getDistrictEntityList() {
		var service = new StateService();
		var state = service.selectById(2);
		
		var list = new ArrayList<District>();
		
		var d1 = new District();
		d1.setName("Bago");
		d1.setBurmese("ပဲခူး");
		d1.setState(state);
		list.add(d1);
		
		var d2 = new District();
		d2.setName("Taungoo");
		d2.setBurmese("တောင်ငူ");
		d2.setState(state);
		list.add(d2);
		
		var d3 = new District();
		d3.setName("Paya");
		d3.setBurmese("ပြေ");
		d3.setState(state);
		list.add(d3);
		
		var d4 = new District();
		d4.setName("Tharrawaddy");
		d4.setBurmese("သာယာဝတီ");
		d4.setState(state);
		list.add(d4);
		
		return Stream.of(Arguments.of(list, 4));
	}
	
	@Order(1)
	@ParameterizedTest
	@CsvFileSource(resources = "/districts_of_states.txt")
	void test_for_insert(String name, String burmese, int stateId, int expectedId) {
		var district = new District();
		district.setName(name);
		district.setBurmese(burmese);
		district.setState(stateService.selectById(stateId));

		var generatedId = districtService.insert(district);
		assertEquals(expectedId, generatedId);
	}

}
