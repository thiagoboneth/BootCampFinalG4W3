package com.mercadolibre.demo.service;

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

public class SectionServiceTest {
    SectionRepository mockSection = Mockito.mock(SectionRepository.class);
    WareHouseRepository mockWhaereHouse = Mockito.mock(WareHouseRepository.class);
    SectionService sectionService = new SectionService(mockSection, mockWhaereHouse);

    @Test
    void testSaveSectionWithSuccess() throws Exception {

        Section section = new Section();
        WareHouse wareHouse = new WareHouse();
        wareHouse.setIdWareHouse(1L);

        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setCapacity(850L);
        sectionDTO.setCategory("Frutas");
        sectionDTO.setIdWareHouse(1L);


        Mockito.when(mockSection.save(Mockito.any(Section.class))).thenReturn(section);
        Mockito.when(mockWhaereHouse.findById(1L)).thenReturn(Optional.of(wareHouse));

        section = sectionService.convertSectionToDTO(sectionDTO);
        section.setIdSection(1l);

        mockSection.save(sectionService.save(sectionDTO));

        assertNotNull(section.getCapacity());
        assertNotNull(section.getCategory());

        assertEquals("Frutas", section.getCategory());
        assertEquals(850L, section.getCapacity());

    }
    @Test
    void testGetListSection() {

        List<Section> sectionList = new ArrayList<>();

        WareHouse wareHouse = new WareHouse();
        wareHouse.setIdWareHouse(1L);
        WareHouse wareHouse2 = new WareHouse();
        wareHouse.setIdWareHouse(2L);

        Section section = new Section();

        section.setCapacity(1500L);
        section.setWareHouse(wareHouse);
        section.setCategory("Frutas");
        sectionList.add(section);

        Section section2 = new Section();
        section2.setCapacity(850L);
        section2.setCategory("Frios");
        section2.setWareHouse(wareHouse2);
        sectionList.add(section2);


        Mockito.when(mockSection.findAll()).thenReturn(sectionList);
        List <Section> listaObtida = mockSection.findAll();


        assertNotNull(listaObtida);
        assertTrue(listaObtida.contains(section));
        assertTrue(listaObtida.contains(section2));

        assertEquals("Frutas", listaObtida.get(0).getCategory());
        assertEquals(850L, listaObtida.get(1).getCapacity());
    }

    @Test
    void testUpdateSectiontNoSuccess() throws Exception {

        WareHouse wareHouse = new WareHouse();
        wareHouse.setIdWareHouse(1L);

        List<Section> sectionList = new ArrayList<>();
        Section section = new Section();
        section.setCapacity(1350L);
        section.setWareHouse(wareHouse);
        section.setCategory("Frios");
        section.setIdSection(1L);
        sectionList.add(section);

        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setCapacity(1350L);
        sectionDTO.setCategory("Frutas");
        sectionDTO.setIdWareHouse(1L);

        Mockito.when(mockWhaereHouse.findById(1L)).thenReturn(Optional.of(wareHouse));
        Mockito.when(mockSection.findById(1L)).thenReturn(Optional.of(section));
        Mockito.when(mockSection.saveAndFlush(section)).thenReturn(section);

        section = sectionService.convertSectionToDTO(sectionDTO);
        section.setIdSection(1L);
        sectionService.update(sectionDTO, 1L);


        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            sectionService.update(sectionDTO, 2L);
        });

        assertThat(exceptionThatWasThrown.getMessage(), equalTo("Id não cadastrado"));
    }

    @Test
    void testUpdateSectionWithSuccess() throws Exception {

        WareHouse wareHouse = new WareHouse();
        wareHouse.setIdWareHouse(1L);

        List<Section> sectionList = new ArrayList<>();
        Section section = new Section();
        section.setCapacity(1350L);
        section.setWareHouse(wareHouse);
        section.setCategory("Frios");
        section.setIdSection(1L);
        sectionList.add(section);

        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setCapacity(1350L);
        sectionDTO.setCategory("Frutas");
        sectionDTO.setIdWareHouse(1L);

        Mockito.when(mockWhaereHouse.findById(1L)).thenReturn(Optional.of(wareHouse));
        Mockito.when(mockSection.findById(1L)).thenReturn(Optional.of(section));
        Mockito.when(mockSection.saveAndFlush(section)).thenReturn(section);

        section = sectionService.convertSectionToDTO(sectionDTO);
        section.setIdSection(1L);
        sectionService.update(sectionDTO, 1L);

        assertNotNull(section.getCapacity());
        assertNotNull(section.getCategory());

        assertEquals("Frutas", section.getCategory());
        assertEquals(1350L, section.getCapacity());
    }
    @Test
    void findByname() {

        WareHouse wareHouse = new WareHouse();

        List<Section> sectionList = new ArrayList<>();
        Section section = new Section();
        section.setCapacity(750L);
        section.setWareHouse(wareHouse);
        section.setCategory("Frios");
        section.setIdSection(1L);
        sectionList.add(section);

        Section section2 = new Section();
        section2.setCapacity(1350L);
        section2.setWareHouse(wareHouse);
        section2.setCategory("Frios");
        section2.setIdSection(2L);
        sectionList.add(section2);

        Mockito.when(mockSection.buscarPorSessao(section.getCategory())).thenReturn(sectionList);

        mockSection.buscarPorSessao(section.getCategory());

        assertEquals("Frios", sectionList.get(0).getCategory());
        assertEquals("Frios", sectionList.get(1).getCategory());

        assertNotNull(sectionList);
    }
    @Test
    void findByID() {

        WareHouse wareHouse = new WareHouse();

        List<Section> sectionList = new ArrayList<>();
        Section section = new Section();
        section.setCapacity(750L);
        section.setWareHouse(wareHouse);
        section.setCategory("Frios");
        section.setIdSection(1L);
        sectionList.add(section);

        Section section2 = new Section();
        section2.setCapacity(1350L);
        section2.setWareHouse(wareHouse);
        section2.setCategory("Frutas");
        section2.setIdSection(2L);
        sectionList.add(section2);

        Mockito.when(mockSection.findById(1L)).thenReturn(Optional.of(sectionList.stream().findAny().get()));
        mockSection.findById(1L);

        assertEquals("Frios", sectionList.get(0).getCategory());
        assertEquals("Frutas", sectionList.get(1).getCategory());

        assertNotNull(sectionList);
    }
    @Test
    void deleteSectionWithSuccess() {

        WareHouse idWareHouse = new WareHouse();
        idWareHouse.setIdWareHouse(1L);

        List<Section> sectionList = new ArrayList<>();
        Section section = new Section();
        section.setCapacity(1350L);
        section.setWareHouse(idWareHouse);
        section.setCategory("Frios");
        section.setIdSection(1L);
        sectionList.add(section);

        sectionService.delete(1L);
        verify(mockSection).deleteById(1L);


    }
}