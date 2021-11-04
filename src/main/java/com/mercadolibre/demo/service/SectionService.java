package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.SectionCategoryDTO;
import com.mercadolibre.demo.dto.SectionDTO;
import com.mercadolibre.demo.dto.SectionTypeDTO;
import com.mercadolibre.demo.model.*;
import com.mercadolibre.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {


    private SectionRepository sectionRepository;
    private WareHouseRepository wareHouseRepository;
    private InboundOrderRepository inboundOrderRepository;
    private BatchStockRepository batchStockRepository;
    private SalesAdRepository salesAdRepository;

    @Autowired
    public SectionService(SectionRepository sectionRepository, WareHouseRepository wareHouseRepository, InboundOrderRepository inboundOrderRepository, BatchStockRepository batchStockRepository, SalesAdRepository salesAdRepository) {
        this.sectionRepository = sectionRepository;
        this.wareHouseRepository = wareHouseRepository;
        this.inboundOrderRepository = inboundOrderRepository;
        this.batchStockRepository = batchStockRepository;
        this.salesAdRepository = salesAdRepository;
    }

    public Section save(SectionDTO dto) throws Exception {
        Section section = convertSectionToDTO(dto);
        return sectionRepository.save(section);
    }

    public List<Section> list() {
        return sectionRepository.findAll();
    }

    public Optional<Section> findById(Long id) {
        return sectionRepository.findById(id);
    }

    public Section update(SectionDTO dto, Long id) throws Exception {

        Optional<Section> existSection = findById(id);
        if (existSection.isPresent()) {
            Section section = convertSectionToDTO(dto);
            section.setSectionCode(id);
            return sectionRepository.saveAndFlush(section);
        } else {
            throw new Exception("Id não cadastrado");
        }
    }

    public List<Section> buscarPorSessao(String name){
        return sectionRepository.buscarPorSessao(name);
    }

    public List<SectionTypeDTO>sectionTypeDTOS(String category){
        List<Section>sections = buscarPorSessao(category);
        List<SectionTypeDTO> sectionTypeDTOS = new ArrayList<>();
        Optional<InboundOrder> inboundOrder;
        Optional<BatchStock> batchStock;
        Optional<SalesAd> salesAd;
        SectionTypeDTO sectionTypeDTO = new SectionTypeDTO();
        for (Section section: sections
             ) {
            inboundOrder = inboundOrderRepository.findById(section.getSectionCode());
            batchStock = batchStockRepository.findById(inboundOrder.get().getBatchStock().getBatchNumber());
            salesAd = salesAdRepository.findById(batchStock.get().getSalesad().getId());
            sectionTypeDTO.setName(category);
            sectionTypeDTO.setQuantity(batchStock.get().getCurrentQuantity());
            sectionTypeDTO.setPrice(salesAd.get().getPrice());
            sectionTypeDTO.setWareHouse(section.getIdWareHouse().getWareHouseName());
            sectionTypeDTO.setNameProduct(salesAd.get().getProduct().getName());

            sectionTypeDTOS.add(sectionTypeDTO);
        }
        return sectionTypeDTOS;
    }

    public void delete(Long id) {
        sectionRepository.deleteById(id);
    }

    public Section convertSectionToDTO(SectionDTO dto) throws Exception {
        Optional<WareHouse> wareHouse = wareHouseRepository.findById(dto.getIdWareHouse());
        if (wareHouse.isPresent()) {
            return new Section(dto.getCapacity(), dto.getCategory(), wareHouse.get());
        } else {
            throw new Exception("Id nao cadastrado");
        }
    }

    public List<SectionCategoryDTO> ListProductForCategory(String category) {
        return sectionRepository.listProductForCategory(category);
    }



}


/*
        String name = dto.getName();
        Long quantity = dto.getQuantity();
        Double price = dto.getPrice();
        String wareHouse = dto.getWareHouse();
        String nameProduct = dto.getNameProduct();

        return new SectionTypeDTO(name,quantity,price,wareHouse,nameProduct);
 */