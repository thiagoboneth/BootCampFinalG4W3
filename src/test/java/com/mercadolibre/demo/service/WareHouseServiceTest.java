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

		List<WareHouse> list = new ArrayList<>();
		WareHouse wareHouse1 = new WareHouse();
		wareHouse1.setIdWareHouse(1L);
		wareHouse1.setWareHouseName("WareHouse 1");
		list.add(wareHouse1);

		WareHouse wareHouse2 = new WareHouse();
		wareHouse2.setIdWareHouse(2L);
		wareHouse2.setWareHouseName("WareHouse 2");
		list.add(wareHouse2);

		Mockito.when(mock.findAll()).thenReturn(list);
		List<WareHouse> listAll = mock.findAll();
		wareHouseService.list();

		assertNotNull(list);

		assertTrue(listAll.contains(wareHouse1));
		assertTrue(listAll.contains(wareHouse2));
	}

	@Test
	void testFindById() {

		List<WareHouse> list = new ArrayList<>();
		WareHouse wareHouse = new WareHouse();
		wareHouse.setIdWareHouse(1L);
		wareHouse.setWareHouseName("WareHouse 1");
		list.add(wareHouse);

		mock.findAll();
		Mockito.when(mock.findById(1L)).thenReturn(Optional.of(list.stream().findAny().get()));
		mock.findById(1L);

		assertEquals(1L, wareHouse.getIdWareHouse());
		assertEquals("WareHouse 1", wareHouse.getWareHouseName());

		assertNotNull(wareHouse.getIdWareHouse());
		assertNotNull(wareHouse.getWareHouseName());
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
	
	@Test
	void deleteWareHouseWithSuccess() throws Exception {
		List<WareHouse> list = new ArrayList<>();
		
		WareHouse wareHouse = new WareHouse();
		wareHouse.setIdWareHouse(1L);
		wareHouse.setWareHouseName("WareHouse 1");
		list.add(wareHouse);

		Mockito.verify(mock).deleteById(1L);
	}
}
