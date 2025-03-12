package com.jdc.ps.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.jdc.ps.entity.Region;
import com.jdc.ps.entity.State;
import com.jdc.ps.service.StateService;

@TestMethodOrder(OrderAnnotation.class)
public final class StateServiceTest extends BaseTest {

	StateService stateService = new StateService();
	
	@Order(4)
	@ParameterizedTest
	@CsvSource({
		"6, 14",
		"4, 13"
	})
	@Disabled
	void test_for_delete(int id, int expectedCount) {
		stateService.delete(id);
		assertEquals(expectedCount, stateService.count());
	}
	
	@Order(3)
	@ParameterizedTest
	@MethodSource(value = "getStateEntityList")
	@Disabled
	void test_for_insert_list(List<State> entities) {
		var insertedCount = stateService.insert(entities);
		assertEquals(3, insertedCount);
		
		assertEquals(7, stateService.count());
	}
	
	static Stream<Arguments> getStateEntityList() {
		List<State> entities = new ArrayList<>();
		
		State kachin = new State();
		kachin.setName("Kachin");
		kachin.setBurmese("ကချင်ပြည်နယ်");
		kachin.setRegion(Region.North);
		kachin.setCapital("Myitkyina");
		kachin.setPopulation(1_689_441);
		
		State kayah = new State();
		kayah.setName("Kayah");
		kayah.setBurmese("ကယာပြည်နယ်");
		kayah.setRegion(Region.North);
		kayah.setCapital("Loikaw");
		kayah.setPopulation(286_627);
		
		State kayin = new State();
		kayin.setName("Kayin");
		kayin.setBurmese("ကရင်ပြည်နယ်");
		kayin.setRegion(Region.Lower);
		kayin.setCapital("Hpa-an");
		kayin.setPopulation(1_574_079);
		
		entities.add(kachin);
		entities.add(kayah);
		entities.add(kayin);
		
		return Stream.of(Arguments.of(entities));
	}
	
	@Order(2)
	@ParameterizedTest
	@CsvSource(delimiter = '\t', value = "Naypyidaw Union Territory	နေပြည်တော် ပြည်ထောင်စုနယ်မြေ	Central	Naypyidaw	1_160_242	4")
	@Disabled
	void test_for_update(String name, String burmese, Region region,
			String capital, int population, int idForUpdate) {
		
		State state = new State();
		state.setName(name);
		state.setBurmese(burmese);
		state.setRegion(region);
		state.setCapital(capital);
		state.setPopulation(population);
		
		stateService.update(idForUpdate, state);
		
		var stateAfterUpdated = stateService.selectById(idForUpdate);
		assertNotNull(stateAfterUpdated);
		assertEquals(name, stateAfterUpdated.getName());
		assertEquals(burmese, stateAfterUpdated.getBurmese());
		assertEquals(region, stateAfterUpdated.getRegion());
		assertEquals(capital, stateAfterUpdated.getCapital());
		assertEquals(population, stateAfterUpdated.getPopulation());
	}
		
	@Order(1)
	@ParameterizedTest
	@CsvFileSource(resources = "/states.txt")
	void test_for_insert(String name, String burmese, Region region, String capital, int population, int expectedId) {

		State state = new State();
		state.setName(name);
		state.setBurmese(burmese);
		state.setRegion(region);
		state.setCapital(capital);
		state.setPopulation(population);

		var count = stateService.count();
		var countBeforeInsert = count;

		int generatedId = stateService.insert(state);
		count = stateService.count();
		
		assertEquals(expectedId, generatedId);
		assertEquals(countBeforeInsert + 1, count);
	}
}
