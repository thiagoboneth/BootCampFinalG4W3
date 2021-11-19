package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.InboundOrderDTO;
import com.mercadolibre.demo.model.BatchStock;
import com.mercadolibre.demo.model.InboundOrder;
import com.mercadolibre.demo.model.Section;
import com.mercadolibre.demo.repository.BatchStockRepository;
import com.mercadolibre.demo.repository.InboundOrderRepository;
import com.mercadolibre.demo.repository.SectionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class InboundOrderServiceTest {

    private BatchStockRepository mockBatchStockRepository = Mockito.mock(BatchStockRepository.class);
    private InboundOrderRepository mockInboundOrderRepository = Mockito.mock(InboundOrderRepository.class);
    private SectionRepository mockSectionRepository = Mockito.mock(SectionRepository.class);

    InboundOrderService inboundOrderService = new InboundOrderService(mockInboundOrderRepository, mockBatchStockRepository,mockSectionRepository);


    BatchStock baseTesteBatchStock(){
        BatchStock batchStock = new BatchStock();
        batchStock.setIdBatchNumber(1L);
        return batchStock;
    }

    Section baseTesteSection(){
        Section section = new Section();
        section.setIdSection(1L);
        return section;
    }

    InboundOrderDTO baseTesteInboundOrderDTO(){
        InboundOrderDTO inboundOrderDTO = new InboundOrderDTO();
        inboundOrderDTO.setIdBatchStock(baseTesteBatchStock().getIdBatchNumber());
        inboundOrderDTO.setIdSection(baseTesteSection().getIdSection());
        return inboundOrderDTO;
    }

    InboundOrder baseTesteInboundOrder(){
        InboundOrder inboundOrder = new InboundOrder();
        inboundOrder.setSection(baseTesteSection());
        inboundOrder.setBatchStock(baseTesteBatchStock());
        inboundOrder.setId(1L);
        return inboundOrder;
    }


    @Test
    void saveInboundOrderWithSuccess() throws Exception {

        baseTesteBatchStock();
        baseTesteSection();
        baseTesteInboundOrderDTO();

        InboundOrder inboundOrder = new InboundOrder();

        Mockito.when(mockSectionRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(baseTesteSection()));
        Mockito.when(mockBatchStockRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(baseTesteBatchStock()));
        Mockito.when(mockInboundOrderRepository.save(Mockito.any(InboundOrder.class))).thenReturn(inboundOrder);

        inboundOrderService.save(baseTesteInboundOrderDTO());
        inboundOrder.setId(1L);

        Assertions.assertNotNull(inboundOrder.getId());

    }

    @Test
    void saveInboundOrderNoSuccess() throws Exception {

        baseTesteBatchStock();
        baseTesteSection();
        InboundOrderDTO inboundOrderDTO = baseTesteInboundOrderDTO();
        inboundOrderDTO.setIdSection(2L);

        InboundOrder inboundOrder = new InboundOrder();

        Mockito.when(mockSectionRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(baseTesteSection()));
        Mockito.when(mockBatchStockRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(baseTesteBatchStock()));
        Mockito.when(mockInboundOrderRepository.save(Mockito.any(InboundOrder.class))).thenReturn(inboundOrder);


        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            inboundOrderService.update(inboundOrderDTO, 3L);
        });

        assertEquals(exceptionThatWasThrown.getMessage(), "InboundOrder não cadastrado");
    }
    @Test
    void convertInboundOrderToDTONoSuccessBecauseBatchStock() throws Exception {
        InboundOrderDTO inboundOrderDTO = baseTesteInboundOrderDTO();
        inboundOrderDTO.setIdSection(3L);
        inboundOrderDTO.setIdBatchStock(3L);

        Mockito.when(mockSectionRepository.findById(1L)).thenReturn(Optional.of(baseTesteSection()));
        Mockito.when(mockBatchStockRepository.findById(1L)).thenReturn(Optional.of(baseTesteBatchStock()));

        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            inboundOrderService.convertInboundOrderToDTO(inboundOrderDTO);
        });
        assertEquals(exceptionThatWasThrown.getMessage(), "BatchStock não encontrado");
    }
    @Test
    void convertInboundOrderToDTONoSuccessBecauseSection() throws Exception {
        InboundOrderDTO inboundOrderDTO = baseTesteInboundOrderDTO();
        inboundOrderDTO.setIdSection(3L);
        inboundOrderDTO.setIdBatchStock(1L);

        Mockito.when(mockSectionRepository.findById(1L)).thenReturn(Optional.of(baseTesteSection()));
        Mockito.when(mockBatchStockRepository.findById(1L)).thenReturn(Optional.of(baseTesteBatchStock()));

        Throwable exceptionThatWasThrown = assertThrows(Exception.class, () -> {
            inboundOrderService.convertInboundOrderToDTO(inboundOrderDTO);
        });
        assertEquals(exceptionThatWasThrown.getMessage(), "Section não encontrada");
    }

    @Test
    void listWithSuccess(){

        baseTesteBatchStock();
        baseTesteSection();

        List<InboundOrder>inboundOrderList = new ArrayList<>();

        baseTesteInboundOrder();
        inboundOrderList.add(baseTesteInboundOrder());

        Mockito.when(mockInboundOrderRepository.findAll()).thenReturn(inboundOrderList);

        inboundOrderService.list();

        assertNotNull(inboundOrderList);

    }

    @Test
    void findByIfWithSuccess(){

        baseTesteSection();
        baseTesteBatchStock();
        baseTesteInboundOrder();

        List<InboundOrder>inboundOrderList = new ArrayList<>();
        inboundOrderList.add(baseTesteInboundOrder());

        Mockito.when(mockInboundOrderRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(baseTesteInboundOrder()));

        Optional<InboundOrder> byId = inboundOrderService.findById(1L);
        assertNotNull(byId);

    }

    @Test
    void updateWithSuccess() throws Exception {

        InboundOrder inboundOrder = baseTesteInboundOrder();
        inboundOrder.setSection(baseTesteSection());

        BatchStock batchStock = baseTesteBatchStock();
        Section section = baseTesteSection();


        InboundOrderDTO inboundOrderDTO = baseTesteInboundOrderDTO();
        inboundOrderDTO.setIdSection(2L);

        Mockito.when(mockSectionRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(section));
        Mockito.when(mockBatchStockRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(batchStock));
        Mockito.when(mockInboundOrderRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(inboundOrder));
        Mockito.when(mockInboundOrderRepository.saveAndFlush(baseTesteInboundOrder())).thenReturn(baseTesteInboundOrder());

        inboundOrderService.update(inboundOrderDTO,1L);
        inboundOrder.setId(1L);

        assertEquals(1L, baseTesteInboundOrder().getId());
        assertEquals(2L, inboundOrderDTO.getIdSection());

        assertNotNull(inboundOrder.getId());
        assertNotNull(inboundOrderDTO.getIdSection());

    }
}
