package com.mercadolibre.demo.service;

import com.mercadolibre.demo.model.Section;
import com.mercadolibre.demo.repository.SectionRepositotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    private final SectionRepositotory sectionRepositotory;

    @Autowired
    public SectionService(SectionRepositotory sectionRepositotory) {
        this.sectionRepositotory = sectionRepositotory;
    }

    public Section save (Section section){
        return sectionRepositotory.save(section);
    }
    public List<Section>list(){
        return sectionRepositotory.findAll();
    }
    public Section update(Section section){
        return sectionRepositotory.saveAndFlush(section);
    }
    public void delete(Long id){
        sectionRepositotory.deleteById(id);
    }

}
