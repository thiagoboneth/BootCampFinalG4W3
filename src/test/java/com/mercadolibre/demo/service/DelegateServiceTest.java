package com.mercadolibre.demo.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mercadolibre.demo.dto.DelegateDTO;
import com.mercadolibre.demo.model.Delegate;
import com.mercadolibre.demo.model.Section;
import com.mercadolibre.demo.model.WareHouse;
import com.mercadolibre.demo.repository.DelegateRepository;
import com.mercadolibre.demo.repository.SectionRepository;
import com.mercadolibre.demo.repository.WareHouseRepository;

public class DelegateServiceTest {

	DelegateRepository mockDelegateRepository = Mockito.mock(DelegateRepository.class);
	SectionRepository mockSectionRepository = Mockito.mock(SectionRepository.class);
	WareHouseRepository mockWareHouseRepository = Mockito.mock(WareHouseRepository.class);

	DelegateService delegateService = new DelegateService(mockDelegateRepository, mockSectionRepository);

	@Test
	void testSaveDelegateWithSuccess() throws Exception {

		DelegateDTO delegateDTO = new DelegateDTO();
		delegateDTO.setName("Roberta");
		delegateDTO.setLastname("Motta");
		delegateDTO.setIdSection(1L);

		WareHouse wareHouse = new WareHouse();
		wareHouse.setIdWareHouse(1L);
		wareHouse.setWareHouseName("WareHouse 1");

		Section section = new Section();
		section.setIdSection(1L);
		section.setCapacity(200L);
		section.setCategory("FRIOS");
		section.setWareHouse(wareHouse);

		Delegate delegate = new Delegate();

		Mockito.when(mockDelegateRepository.save(Mockito.any(Delegate.class))).thenReturn(delegate);
		Mockito.when(mockWareHouseRepository.findById(1L)).thenReturn(Optional.of(wareHouse));
		Mockito.when(mockSectionRepository.findById(1L)).thenReturn(Optional.of(section));
		Mockito.when(mockDelegateRepository.findById(1L)).thenReturn(Optional.of(delegate));

		delegate = delegateService.convertDelegateToDTO(delegateDTO);
		
		mockDelegateRepository.save(delegateService.save(delegateDTO));
		delegate.setIdDelegate(1L);

		assertEquals(1L, delegate.getIdDelegate());
		assertEquals("Roberta", delegate.getName());
		assertEquals("Motta", delegate.getLastname());
		assertEquals(1L, delegate.getIdSection().getIdSection());

		assertNotNull(delegate.getIdDelegate());
		assertNotNull(delegate.getName());
		assertNotNull(delegate.getLastname());
		assertNotNull(delegate.getIdDelegate());
	}
	
	@Test
	void testGetSectionWithSuccessful() throws Exception {
		
		DelegateDTO delegateDTO = new DelegateDTO();
		delegateDTO.setName("Roberta");
		delegateDTO.setLastname("Motta");
		delegateDTO.setIdSection(1L);
		
		List<WareHouse> wareHouseList = new ArrayList<>();
		WareHouse wareHouse = new WareHouse();
		wareHouse.setIdWareHouse(1L);
		wareHouse.setWareHouseName("WareHouse 1");
		wareHouseList.add(wareHouse);
		
		List<Section> sectionList = new ArrayList<>();
		Section section = new Section();
		section.setIdSection(1L);
		section.setCapacity(200L);
		section.setCategory("FRIOS");
		section.setWareHouse(wareHouse);
		sectionList.add(section);
		
		Mockito.when(mockSectionRepository.findById(1L)).thenReturn(Optional.of(sectionList.stream().findAny().get()));
		
		Section getSection = delegateService.getSection(delegateDTO).get();
		
		assertEquals(1L, getSection.getIdSection());
		assertEquals(200L, getSection.getCapacity());
		assertEquals("FRIOS", getSection.getCategory());
		assertEquals(wareHouse, getSection.getWareHouse());
		
		assertNotNull(getSection.getIdSection());
		assertNotNull(getSection.getCapacity());
		assertNotNull(getSection.getCategory());
		assertNotNull(getSection.getWareHouse());
	}
	
	@Test
	void testListDelegate() {
		
		List<WareHouse> wareHouseList = new ArrayList<>();
		WareHouse wareHouse = new WareHouse();
		wareHouse.setIdWareHouse(1L);
		wareHouse.setWareHouseName("WareHouse 1");
		wareHouseList.add(wareHouse);
		
		List<Section> sectionList = new ArrayList<>();
		Section section = new Section();
		section.setIdSection(1L);
		section.setCapacity(150L);
		section.setCategory("QUENTES");
		section.setWareHouse(wareHouse);
		sectionList.add(section);
		
		List<Delegate> delegateList = new ArrayList<>();
		Delegate delegate = new Delegate();
		delegate.setIdDelegate(1L);
		delegate.setName("Katarina");
		delegate.setLastname("Romanov");
		delegate.setIdSection(section);
		
		Mockito.when(mockDelegateRepository.findAll()).thenReturn(delegateList);
		delegateList = mockDelegateRepository.findAll();
		delegateService.list();
		
		assertEquals(1L, delegate.getIdDelegate());
		assertEquals("Katarina", delegate.getName());
		assertEquals("Romanov", delegate.getLastname());
		assertEquals(section, delegate.getIdSection());
		
		assertNotNull(delegateList);
		assertNotNull(delegate.getIdDelegate());
		assertNotNull(delegate.getName());
		assertNotNull(delegate.getLastname());
		assertNotNull(delegate.getIdSection());
	}
	
	@Test
	void testUpdateDelegateWithSuccess() throws Exception {
		
		List<WareHouse> wareHouseList = new ArrayList<>();
		WareHouse wareHouse = new WareHouse();
		wareHouse.setIdWareHouse(1L);
		wareHouse.setWareHouseName("WareHouse 1");
		wareHouseList.add(wareHouse);

		List<Section> sectionList = new ArrayList<>();
		Section section = new Section();
		section.setIdSection(1L);
		section.setCapacity(200L);
		section.setCategory("FRIOS");
		section.setWareHouse(wareHouse);
		sectionList.add(section);

		Delegate delegate = new Delegate();
		delegate.setIdDelegate(1L);
		delegate.setName("Roberta");
		delegate.setLastname("Motta");
		delegate.setIdSection(section);
		
		DelegateDTO delegateDTO = new DelegateDTO();
		delegateDTO.setName("Flávia");
		delegateDTO.setLastname("Braganca");
		delegateDTO.setIdSection(1L);

	
		Mockito.when(mockWareHouseRepository.findById(1L)).thenReturn(Optional.of(wareHouse));
		Mockito.when(mockSectionRepository.findById(1L)).thenReturn(Optional.of(section));
		Mockito.when(mockDelegateRepository.findById(1L)).thenReturn(Optional.of(delegate));
		Mockito.when(mockDelegateRepository.saveAndFlush(Mockito.any(Delegate.class))).thenReturn(delegate);

		delegate = delegateService.convertDelegateToDTO(delegateDTO);
		
		delegateService.update(delegateDTO, mockDelegateRepository.findById(1L).get().getIdDelegate());
		delegate.setIdDelegate(1L);

		assertEquals(1L, delegate.getIdDelegate());
		assertEquals("Flávia", delegate.getName());
		assertEquals("Braganca", delegate.getLastname());
		assertEquals(1L, delegate.getIdSection().getIdSection());

		assertNotNull(delegate.getIdDelegate());
		assertNotNull(delegate.getName());
		assertNotNull(delegate.getLastname());
		assertNotNull(delegate.getIdSection());
		
	}
	
	@Test
	void testUpdateDelegateNoSuccess() throws Exception {
		
		List<WareHouse> wareHouseList = new ArrayList<>();
		WareHouse wareHouse = new WareHouse();
		wareHouse.setIdWareHouse(1L);
		wareHouse.setWareHouseName("WareHouse 1");
		wareHouseList.add(wareHouse);

		List<Section> sectionList = new ArrayList<>();
		Section section = new Section();
		section.setIdSection(1L);
		section.setCapacity(200L);
		section.setCategory("FRIOS");
		section.setWareHouse(wareHouse);
		sectionList.add(section);

		Delegate delegate = new Delegate();
		delegate.setIdDelegate(1L);
		delegate.setName("Roberta");
		delegate.setLastname("Motta");
		delegate.setIdSection(section);
		
		DelegateDTO delegateDTO = new DelegateDTO();
		delegateDTO.setName("Flávia");
		delegateDTO.setLastname("Braganca");
		delegateDTO.setIdSection(1L);

	
		Mockito.when(mockWareHouseRepository.findById(1L)).thenReturn(Optional.of(wareHouse));
		Mockito.when(mockSectionRepository.findById(1L)).thenReturn(Optional.of(section));
		Mockito.when(mockDelegateRepository.findById(1L)).thenReturn(Optional.of(delegate));
		Mockito.when(mockDelegateRepository.saveAndFlush(Mockito.any(Delegate.class))).thenReturn(delegate);

		delegate = delegateService.convertDelegateToDTO(delegateDTO);
		
		delegateService.update(delegateDTO, mockDelegateRepository.findById(1L).get().getIdDelegate());
		delegate.setIdDelegate(1L);

        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            delegateService.update(delegateDTO, 2L);
        });

        assertThat(exceptionThatWasThrown.getMessage(), equalTo("Id não cadastrado"));
		
	}
	
	@Test
	void deleteDelegateWithSuccess() {
		
		List<WareHouse> wareHouseList = new ArrayList<>();
		WareHouse wareHouse = new WareHouse();
		wareHouse.setIdWareHouse(1L);
		wareHouse.setWareHouseName("WareHouse 1");
		wareHouseList.add(wareHouse);

		List<Section> sectionList = new ArrayList<>();
		Section section = new Section();
		section.setIdSection(1L);
		section.setCapacity(200L);
		section.setCategory("FRIOS");
		section.setWareHouse(wareHouse);
		sectionList.add(section);

		Delegate delegate = new Delegate();
		delegate.setIdDelegate(1L);
		delegate.setName("Roberta");
		delegate.setLastname("Motta");
		delegate.setIdSection(section);
		
		DelegateDTO delegateDTO = new DelegateDTO();
		delegateDTO.setName("Flávia");
		delegateDTO.setLastname("Braganca");
		delegateDTO.setIdSection(1L);
		
		delegateService.delete(1L);
		Mockito.verify(mockDelegateRepository).deleteById(1L);
	}
	
}
