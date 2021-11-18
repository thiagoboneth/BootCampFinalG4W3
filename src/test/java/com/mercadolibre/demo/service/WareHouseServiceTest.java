package com.mercadolibre.demo.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mercadolibre.demo.dto.WareHouseDTO;
import com.mercadolibre.demo.model.WareHouse;
import com.mercadolibre.demo.repository.WareHouseRepository;

class WareHouseServiceTest {

	WareHouseRepository mock = Mockito.mock(WareHouseRepository.class);
	WareHouseService wareHouseService = new WareHouseService(mock);


	List<WareHouse> baseTesteWhareHouseList(){
		List<WareHouse> wareHouseList = new ArrayList<>();

		WareHouse wareHouse = new WareHouse();
		wareHouse.setIdWareHouse(1L);
		wareHouse.setWareHouseName("WhareHouse 1");
		wareHouseList.add(wareHouse);

		WareHouse wareHouse2 = new WareHouse();
		wareHouse.setIdWareHouse(2L);
		wareHouse.setWareHouseName("WhareHouse 2");
		wareHouseList.add(wareHouse2);

		WareHouse wareHouse3 = new WareHouse();
		wareHouse.setIdWareHouse(3L);
		wareHouse.setWareHouseName("WhareHouse 3");
		wareHouseList.add(wareHouse3);

		return wareHouseList;
	}



	@Test
	void testSaveWareHouseWithSuccess() {

		WareHouseDTO dto = new WareHouseDTO();
		dto.setWareHouseName("WareHouse 1");

		WareHouse wareHouse = wareHouseService.convertWareHouseToDTO(dto);
		Mockito.when(mock.save(Mockito.any(WareHouse.class))).thenReturn(wareHouseService.convertWareHouseToDTO(dto));
		mock.save(wareHouseService.save(dto));

		assertEquals("WareHouse 1", wareHouse.getWareHouseName());

		assertNotNull(wareHouse.getWareHouseName());
	}

	@Test
	void testListWareHouseWithSuccess() {

		List<WareHouse> list = baseTesteWhareHouseList();

		Mockito.when(mock.findAll()).thenReturn(list);
		List<WareHouse> listAll = mock.findAll();
		wareHouseService.list();

		assertNotNull(list);
		assertEquals(3, listAll.size());

	}

	@Test
	void testFindById() {

		List<WareHouse> list = baseTesteWhareHouseList();

		mock.findAll();
		Mockito.when(mock.findById(1L)).thenReturn(Optional.of(list.stream().findAny().get()));
		Optional<WareHouse> byId = mock.findById(1L);

		assertEquals(3L, byId.get().getIdWareHouse());
		assertEquals("WhareHouse 3", byId.get().getWareHouseName());

		assertNotNull(byId.get().getIdWareHouse());
		assertNotNull(byId.get().getWareHouseName());
	}

	@Test
	void testUpdateWareHouseWithSuccess() throws Exception {

		List<WareHouse> list = new ArrayList<>();
		WareHouse wareHouse = new WareHouse();
		wareHouse.setIdWareHouse(1L);
		wareHouse.setWareHouseName("WareHouse 1");
		list.add(wareHouse);

		WareHouseDTO dto = new WareHouseDTO();
		dto.setWareHouseName("WareHouse 1A");

		Mockito.when(mock.findById(1L)).thenReturn(Optional.of(list.stream().findAny().get()));
		Mockito.when(mock.saveAndFlush(wareHouse)).thenReturn(wareHouse);

		wareHouse = wareHouseService.convertWareHouseToDTO(dto);
		wareHouse.setIdWareHouse(1L);
		wareHouseService.update(dto, mock.findById(1L).get().getIdWareHouse());

		assertEquals(1L, wareHouse.getIdWareHouse());
		assertEquals("WareHouse 1A", wareHouse.getWareHouseName());

		assertNotNull(wareHouse.getIdWareHouse());
		assertNotNull( wareHouse.getWareHouseName());
	}

	@Test
	void testUpdateWareHouseNoSuccess() {

		List<WareHouse> list = new ArrayList<>();
		WareHouse wareHouse = new WareHouse();
		wareHouse.setIdWareHouse(1L);
		wareHouse.setWareHouseName("WareHouse 1");
		list.add(wareHouse);

		WareHouseDTO dto = new WareHouseDTO();
		dto.setWareHouseName("WareHouse 1B");

		Mockito.when(mock.findById(1L)).thenReturn(Optional.of(list.stream().findAny().get()));
		Mockito.when(mock.saveAndFlush(wareHouse)).thenReturn(wareHouse);

		wareHouse = wareHouseService.convertWareHouseToDTO(dto);
		wareHouse.setIdWareHouse(1L);

		Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
			wareHouseService.update(dto, 2L);
		});

		assertThat(exceptionThatWasThrown.getMessage(), equalTo("Id não cadastrado"));
	}
}
