/*
package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.SectionDTO;
import com.mercadolibre.demo.dto.SectionTypeDTO;
import com.mercadolibre.demo.model.*;
import com.mercadolibre.demo.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

public class SectionServiceTest {
    private SectionRepository mockSectionRepository = Mockito.mock(SectionRepository.class);
    private WareHouseRepository mockWareHouseRepository = Mockito.mock(WareHouseRepository.class);
    private InboundOrderRepository mockInboundOrderRepository = Mockito.mock(InboundOrderRepository.class);
    private BatchStockRepository mockBatchStockRepository = Mockito.mock(BatchStockRepository.class);
    private SalesAdRepository mockSalesAdRepository = Mockito.mock(SalesAdRepository.class);
    SectionService sectionService = new SectionService(mockSectionRepository, mockWareHouseRepository,mockInboundOrderRepository,
            mockBatchStockRepository,mockSalesAdRepository);

    @Test
    void testSaveSectionWithSuccess() throws Exception {
        WareHouse wareHouse = new WareHouse();
        wareHouse.setIdWareHouse(1L);

        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setCapacity(850L);
        sectionDTO.setCategory("CONGELADO");
        sectionDTO.setIdWareHouse(1L);

        WareHouse wh = new WareHouse();
        wh.setIdWareHouse(1L);

        Mockito.when(mockWareHouseRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(wh));

        Section section = new Section(sectionDTO.getCapacity(), sectionDTO.getCategory(), wh);
        section.setIdSection(1L);

        Mockito.when(mockSectionRepository.save(Mockito.any(Section.class))).thenReturn(section);

        sectionService.save(sectionDTO);

        assertNotNull(section.getCapacity());
        assertNotNull(section.getCategory());
        assertNotNull(section.getWareHouse());
        assertNotNull(section.getIdSection());

        assertEquals("CONGELADO", section.getCategory());
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


        Mockito.when(mockSectionRepository.findAll()).thenReturn(sectionList);
        List <Section> listaObtida = sectionService.list();


        assertNotNull(listaObtida);
        assertTrue(listaObtida.contains(section));
        assertTrue(listaObtida.contains(section2));

        assertEquals("Frutas", listaObtida.get(0).getCategory());
        assertEquals(850L, listaObtida.get(1).getCapacity());
    }
    @Test
    void testGetWareHouse() {
        WareHouse wareHouse = new WareHouse();
        wareHouse.setIdWareHouse(1L);

        InboundOrder inboundOrder = new InboundOrder();
        inboundOrder.setId(1L);


        BatchStock batchStock = new BatchStock();
        batchStock.setIdBatchNumber(1L);

        SalesAd salesAd = new SalesAd();
        salesAd.setId(1L);

        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setCapacity(850L);
        sectionDTO.setCategory("CONGELADO");
        sectionDTO.setIdWareHouse(1L);
        List<WareHouse> wareHouseList = new ArrayList<>();

        Mockito.when(mockWareHouseRepository.findById(1L)).thenReturn(Optional.of(wareHouse));

        assertNotNull(sectionDTO.getCapacity());
        assertNotNull(sectionDTO.getCategory());
        assertNotNull(sectionDTO.getIdWareHouse());

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

        Mockito.when(mockWareHouseRepository.findById(1L)).thenReturn(Optional.of(wareHouse));
        Mockito.when(mockSectionRepository.findById(1L)).thenReturn(Optional.of(section));
        Mockito.when(mockSectionRepository.saveAndFlush(section)).thenReturn(section);

        section = sectionService.convertSectionToDTO(sectionDTO);
        section.setIdSection(1L);
        sectionService.update(sectionDTO, 1L);

        assertNotNull(section.getCapacity());
        assertNotNull(section.getCategory());

        assertEquals("Frutas", section.getCategory());
        assertEquals(1350L, section.getCapacity());
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

        Mockito.when(mockWareHouseRepository.findById(1L)).thenReturn(Optional.of(wareHouse));
        Mockito.when(mockSectionRepository.findById(1L)).thenReturn(Optional.of(section));
        Mockito.when(mockSectionRepository.saveAndFlush(section)).thenReturn(section);

        section = sectionService.convertSectionToDTO(sectionDTO);
        section.setIdSection(1L);
        sectionService.update(sectionDTO, 1L);


        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            sectionService.update(sectionDTO, 2L);
        });

        assertThat(exceptionThatWasThrown.getMessage(), equalTo("Id não cadastrado"));
    }
    @Test
    void findSectionBySessao() {

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

        Section section3 = new Section();
        section3.setCapacity(1350L);
        section3.setWareHouse(wareHouse);
        section3.setCategory("Congelados");
        section3.setIdSection(2L);
        sectionList.add(section3);
        Mockito.when(mockSectionRepository.findByCategoryContaining("Frios")).thenReturn(sectionList);
        Mockito.when(mockSectionRepository.findAll()).thenReturn(sectionList);
        mockSectionRepository.findById(1L);
        mockSectionRepository.findByCategoryContaining("Frios");
        List<Section> listGet = mockSectionRepository.findByCategoryContaining("Frios");

        assertEquals("Frios", listGet.get(0).getCategory());
        assertEquals("Frios", listGet.get(1).getCategory());

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

        Mockito.when(mockSectionRepository.findById(1L)).thenReturn(Optional.of(sectionList.stream().findAny().get()));
        mockSectionRepository.findById(1L);

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
        verify(mockSectionRepository).deleteById(1L);

    }
    @Test
    void testBuscarPorSessaoSucess(){

        WareHouse idWareHouse = new WareHouse();
        idWareHouse.setIdWareHouse(1L);

        List<Section> sectionList = new ArrayList<>();
        Section section = new Section();
        section.setCapacity(1350L);
        section.setWareHouse(idWareHouse);
        section.setCategory("Frios");
        section.setIdSection(1L);
        sectionList.add(section);

        sectionService.findSectionCategories("Frios");
        assertNotNull(sectionList.contains(section.getCategory()));
    }

    @Test
    void testFindSectionCategoriesSuccess(){

        Section section = new Section(1L, 100L, "Congelados", new WareHouse(2L, "trenheira"));
        List<Section> sectionList = Arrays.asList(section);


        Mockito.when(mockSectionRepository.findByCategoryContaining(Mockito.any(String.class))).thenReturn(sectionList);


        SectionTypeDTO sectionTypeDTO = sectionService.setSectionTypeDTO("category",
                100L,50.0,"WarehouseName","ProductName");

        sectionService.findSectionCategories("Congelados");
        assertNotNull(sectionList.contains(sectionTypeDTO.getName()));

    }

    @Test
    void testGetSectionIdSucess(){

        List<Section>sections = new ArrayList<>();

        Section section = new Section();
        section.setIdSection(1L);
        sections.add(section);

        Mockito.when(mockSectionRepository.findById(1L)).thenReturn(Optional.of(sections.stream().findAny().get()));
       // Section getSection = sectionService.getSectionId(1L).get();

       // assertEquals(1L,getSection.getIdSection());
    }

    @Test
    void testGetBatchStockId(){

        List<BatchStock>batchStockList = new ArrayList<>();

        BatchStock batchStock = new BatchStock();
        batchStock.setIdBatchNumber(1L);
        batchStockList.add(batchStock);

        Mockito.when(mockBatchStockRepository.findById(1L)).thenReturn(Optional.of(batchStockList.stream().findAny().get()));
        BatchStock getBatchStock = sectionService.getBatchStockId(1L).get();

        assertEquals(1L,getBatchStock.getIdBatchNumber());

    }

    @Test
    void testGetSalesAdIdSucess(){

        List<SalesAd>salesAdList = new ArrayList<>();

        SalesAd salesAd = new SalesAd();
        salesAd.setId(1L);
        salesAdList.add(salesAd);

        Mockito.when(mockSalesAdRepository.findById(1L)).thenReturn(Optional.of(salesAdList.stream().findAny().get()));
        SalesAd getSalesAd = sectionService.getSalesAdId(1L).get();

        assertEquals(1L,getSalesAd.getId());

    }

    @Test
    void testSetSectionTypeDTO(){

        WareHouse wareHouse1 = new WareHouse();
        wareHouse1.setIdWareHouse(1L);
        wareHouse1.setWareHouseName("WareHouse 1");

        SectionTypeDTO getSectionId = sectionService.setSectionTypeDTO("nome",100L,50.0,
                "wareHouseName","productName");

        assertEquals(50.0,getSectionId.getPrice());
    }
}
*/
