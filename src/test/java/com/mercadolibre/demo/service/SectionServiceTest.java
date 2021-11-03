package com.mercadolibre.demo.service;


import com.mercadolibre.demo.dto.SalesAdDTO;
import com.mercadolibre.demo.dto.SectionDTO;
import com.mercadolibre.demo.model.*;
import com.mercadolibre.demo.repository.SectionRepository;
import com.mercadolibre.demo.repository.WareHouseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SectionServiceTest {
    SectionRepository mockSection = Mockito.mock(SectionRepository.class);
    WareHouseRepository mockWhaereHouse = Mockito.mock(WareHouseRepository.class);
    SectionService sectionService = new SectionService(mockSection, mockWhaereHouse);

    @Test
    void testSaveSectionWithSuccess() throws Exception {

        WareHouse wareHouse = new WareHouse();
        wareHouse.setIdWareHouse(1L);

        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setCapacity(850L);
        sectionDTO.setCategory("Frutas");
        sectionDTO.setIdWareHouse(1L);
        Section section = new Section();

        Mockito.when(mockSection.save(Mockito.any(Section.class))).thenReturn(section);
        Mockito.when(mockWhaereHouse.findById(1L)).thenReturn(Optional.of(wareHouse));

        section = sectionService.convertSectionToDTO(sectionDTO);
        section.setSectionCode(1l);

        mockSection.save(sectionService.save(sectionDTO));

        assertNotNull(section.getCapacity());
        assertNotNull(section.getCategory());

        assertEquals("Frutas", section.getCategory());
        assertEquals(850L, section.getCapacity());

    }

    @Test
    void testUpdateSalesAdtNoSuccess() throws Exception {

        List<Section> sectionList = new ArrayList<>();

        WareHouse wareHouse = new WareHouse();
        wareHouse.setIdWareHouse(1L);

        Section section = new Section();

        section.setCapacity(1500L);
        section.setIdWareHouse(wareHouse);
        section.setCategory("Frutas");

        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setCapacity(850L);
        sectionDTO.setCategory("Frios");
        sectionDTO.setIdWareHouse(1L);


        Mockito.when(mockWhaereHouse.findById(1L)).thenReturn(Optional.of(wareHouse));

        when(mockSection.saveAndFlush(section)).thenReturn(section);

        section = sectionService.convertSectionToDTO(sectionDTO);
        section.setSectionCode(1L);

        section = sectionService.update(sectionDTO,1L);
        section.setSectionCode(1l);



        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            sectionService.update(sectionDTO, 2L);
        });

        assertThat(exceptionThatWasThrown.getMessage(), equalTo("Id não cadastrado"));
    }

    @Test
    void testUpdateSalesAdWithSuccess() throws Exception {

        WareHouse wareHouse = new WareHouse();
        wareHouse.setIdWareHouse(1L);

        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setCapacity(850L);
        sectionDTO.setCategory("Frutas");
        sectionDTO.setIdWareHouse(1L);
        Section section = new Section();

        section.setCapacity(1350L);
        section.setIdWareHouse(wareHouse);
        section.setCategory("Frios");

        Mockito.when(mockSection.save(Mockito.any(Section.class))).thenReturn(section);
        Mockito.when(mockWhaereHouse.findById(1L)).thenReturn(Optional.of(wareHouse));

        section = sectionService.update(sectionDTO,1L);
        section.setSectionCode(1l);

        section = sectionService.convertSectionToDTO(sectionDTO);
        section.setSectionCode(1L);
        sectionService.update(sectionDTO, mockSection.findById(1L).get().getSectionCode());

        assertNotNull(section.getCapacity());
        assertNotNull(section.getCategory());

        assertEquals("Frios", section.getCategory());
        assertEquals(1350L, section.getCapacity());
    }

    @Test
    void deleteSalesAdtWithSuccess() {

        WareHouse idWareHouse = new WareHouse();
        idWareHouse.setIdWareHouse(1L);

        List<Section> sectionList = new ArrayList<>();
        Section section = new Section();
        section.setCapacity(1350L);
        section.setIdWareHouse(idWareHouse);
        section.setCategory("Frios");
        section.setSectionCode(1L);
        sectionList.add(section);

        sectionService.delete(1L);
        verify(mockSection).deleteById(1L);


    }
}