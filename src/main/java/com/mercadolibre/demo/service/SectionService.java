package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.SectionDTO;
import com.mercadolibre.demo.model.Section;
import com.mercadolibre.demo.model.WareHouse;
import com.mercadolibre.demo.repository.SectionRepositotory;
import com.mercadolibre.demo.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {


    private SectionRepositotory sectionRepository;
    private WareHouseRepository wareHouseRepository;

    @Autowired
    public SectionService(SectionRepositotory sectionRepository, WareHouseRepository wareHouseRepository) {
        this.sectionRepository = sectionRepository;
        this.wareHouseRepository = wareHouseRepository;
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

    public void delete(Long id) {
        sectionRepository.deleteById(id);
    }

    public Section convertSectionToDTO(SectionDTO dto) throws Exception {
        Optional<WareHouse> wareHouse = wareHouseRepository.findById(dto.getIdWareHouse());
        if (wareHouse.isPresent()) {
            return new Section(dto.getCapacity(), wareHouse.get());
        } else {
            throw new Exception("Id nao cadastrado");
        }
    }
}
