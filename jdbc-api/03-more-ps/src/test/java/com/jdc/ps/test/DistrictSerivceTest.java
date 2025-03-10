package com.jdc.ps.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.ps.entity.District;
import com.jdc.ps.service.DistrictService;
import com.jdc.ps.service.StateService;

@TestMethodOrder(OrderAnnotation.class)
public class DistrictSerivceTest {
	
	DistrictService districtService = new DistrictService();
	StateService stateService = new StateService();
	
	void test_for_delete() {}
	void test_for_select_by_id() {}
	void test_for_insert_entities() {}
	void test_for_update() {}
	
	@Order(1)
	@ParameterizedTest
	@CsvSource({
		"Pathein, ပုသိမ်, 1, 1",
		"Kyonpyaw, ကျုံပျော်, 1, 2",
		"Hinthada, ဟင်္သာတ, 1, 3",
		"Labutta, လပွတ္တာ, 1, 4",
		"Maubin, မအူပင်, 1, 5",
		"Myanaung, မြန်အောင်, 1, 6",
		"Myaungmya, မြောင်းမြ, 1, 7",
		"Pyapon, ဖျာပုံ, 1, 8"
	})
	void test_for_insert(String name, String burmese, int stateId, int expectedId) {
		var district = new District();
		district.setName(name);
		district.setBurmese(burmese);
		district.setState(stateService.selectById(stateId));

		var generatedId = districtService.insert(district);
		assertEquals(expectedId, generatedId);
	}

}
