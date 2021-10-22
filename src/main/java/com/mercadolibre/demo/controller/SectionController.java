package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.dto.SectionDTO;
import com.mercadolibre.demo.dto.response.SectionResponseDTO;
import com.mercadolibre.demo.model.Section;
import com.mercadolibre.demo.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-sections/section")
public class SectionController {

    private SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }
    @PostMapping(value = "/save")
    public ResponseEntity<SectionResponseDTO> saveSection(@Valid @RequestBody SectionDTO dto){
        Section section = sectionService.save(dto.convertObject());
        return new ResponseEntity<>(SectionResponseDTO.convertSection(section), HttpStatus.CREATED);
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<List<Section>> listSection(){
        List<Section> sections = sectionService.list();
        return new ResponseEntity<>(sections, HttpStatus.OK);
    }
    @PutMapping(value = "/update")
    @ResponseBody
    public ResponseEntity<Section> updateSection(@Valid @RequestBody Section section){
        Section s = sectionService.update(section);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<String> deleteSection(@RequestParam Long idsection){
        sectionService.delete(idsection);
        return new ResponseEntity<>("Section successfully deleted", HttpStatus.OK);
    }
}
