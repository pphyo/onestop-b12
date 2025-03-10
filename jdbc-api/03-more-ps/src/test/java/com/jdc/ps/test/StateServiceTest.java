package com.jdc.ps.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.ps.entity.Region;
import com.jdc.ps.entity.State;
import com.jdc.ps.service.StateService;

@TestMethodOrder(OrderAnnotation.class)
public final class StateServiceTest extends BaseTest {
	
	StateService stateService = new StateService();
	
	@Order(1)
	@ParameterizedTest
	@CsvSource(value = {
		"Ayeyarwady Region	ဧရာဝတီတိုင်းဒေသကြီး	Lower	Pathein	6_184_829	1",
		"Bago Region	ပဲခူးတိုင်းဒေသကြီး	Lower	Bago	4_867_373	2",
		"Chin State	ချင်းပြည်နယ်	West	Hakha	478_801	3"
	}, delimiter = '\t')
	void test_for_insert(String name, String burmese, Region region, 
			String capital, int population, int expectedId) {
		
		State state = new State();
		state.setName(name);
		state.setBurmese(burmese);
		state.setRegion(region);
		state.setCapital(capital);
		state.setPopulation(population);
		
		int generatedId = stateService.insert(state);
		
		assertEquals(expectedId, generatedId);
	}

}










