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
		",a,,,8"
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
	@CsvSource({
		"Pathein, ပုသိမ်, 1, 1",
		"Kyonpyaw, ကျုံပြော်, 1, 2",
		"Hinthada, ဟင်္သာတ, 1, 3",
		"Labutta, လပွတ္တာ, 1, 4",
		"Maubin, မအူပင်, 1, 5",
		"Myanaung, မြန်အောင်, 1, 6",
		"Myaungmya, မြောင်းမြ, 1, 7",
		"Pyapon, ဖျာပုံ, 1, 8",
		"Tedim, တီးတိန်",
		"Falam, ဖလမ်း",
		"Hakha, ဟားခါး",
		"Matupi, မတူပီ",
		"Mindat, မင်းတပ်",
		"Bhamo, ဗန်းမော်",
		"Mohnyin, မိုးညှင်း",
		"Myitkyina, မြစ်ကြီးနား",
		"Putao, ပူတာအို",
		"Bawlakhe, ဘော်လခဲ",
		"Demoso, ဒီးမော့ဆို",
		"Loikaw, လွိုင်ကော်",
		"Mese, မယ်စဲ့",
		"Hpa-an, ဘားအံ",
		"Hpapun, ဖာပွန်",
		"Kawkareik, ကော့ကရိတ်",
		"Myawaddy, မြဝတီ",
		"Magway, မကွေး",
		"Gangaw, ဂန့်ဂေါ်",
		"Minbu, မင်းဘူး",
		"Pakokku, ပခုက္ကူ",
		"Thayet, သရက်",
		"Mandalay, မန္တလေး",
		"Kyaukse, ကျောက်ဆည်",
		"Meiktila, မိတ္ထီလာ",
		"Myingyan, မြင်းခြံ",
		"Nyaung-U, ညောင်ဦး",
		"Pyinoolwin, ပြင်ဦးလွင်",
		"Yamethin, ရမည်းသင်း",
		"Mawlamyine, မော်လမြိုင်",
		"Thaton, သထုံ",
		"Ottara, ဥတ္တရ",
		"Dekkhina, ဒက္ခိဏ",
		"Sittwe, စစ်တွေ",
		"Kyaukpyu, ကျောက်ဖြူ",
		"Maungdaw, မောင်တော",
		"Thandwe, သံတွဲ",
		"Mrauk-U, မြောက်ဦး",
		"Sagaing, စစ်ကိုင်း",
		"Hkamti, ခန္တီး",
		"Kanbalu, ကန့်ဘလူ",
		"Kale, ကလေး",
		"Katha, ကသာ",
		"Mawlaik, မော်လိုက်",
		"Monywa, မုံရွာ",
		"Shwebo, ရွှေဘို",
		"Tamu, တမူး",
		"Yinmabin, ယင်းမပင်",
		"Kawlin, ကော်လင်း",
		"Kengtung, ကျိုင်းတုံ",
		"Mong Hpayak, မိုင်းဖျောက်",
		"Mong Hsat, မိုင်းဆတ်",
		"Tachileik, တာချီလိတ်",
		"Kunlong, ကွမ်းလုံ",
		"Kyaukme, ကျောက်မဲ",
		"Lashio, လားရှိုး",
		"Mu Se, မူဆယ်",
		"Mongmit, မိုးမိတ်",
		"Kokang, ကိုးကန့်",
		"Pa Laung, ပလောင်",
		"Wa, ဝ",
		"Langkho, လင်းခေး",
		"Loilen, လွိုင်လင်",
		"Taunggyi, တောင်ကြီး",
		"Danu, ဓနု",
		"Pa-O, ပအို့ဝ်",
		"Dawei, ထားဝယ်",
		"Kawthaung, ကော့သောင်း",
		"Myeik, မြိတ်",
		"East Yangon, အရှေ့ပိုင်း",
		"North Yangon, မြောက်ပိုင်း",
		"South Yangon, တောင်ပိုင်း",
		"West Yangon, အနောက်ပိုင်း"
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
