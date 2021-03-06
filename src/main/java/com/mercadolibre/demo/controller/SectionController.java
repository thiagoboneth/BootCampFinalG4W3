package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.config.SecurityController;
import com.mercadolibre.demo.dto.SectionDTO;
import com.mercadolibre.demo.dto.SectionTypeDTO;
import com.mercadolibre.demo.dto.response.WareHouseProductItensDTO;
import com.mercadolibre.demo.model.Section;
import com.mercadolibre.demo.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/section")
public class SectionController implements SecurityController {

	@Autowired
	private SectionService sectionService;

	@PostMapping(value = "/save")
	public ResponseEntity<Section> saveSection(@Valid @RequestBody SectionDTO dto) throws Exception{
		try {
			Section section = sectionService.save(dto);
			return new ResponseEntity<>(section, HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity(e.getMessage(),  HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/list")
	@ResponseBody
	public ResponseEntity<List<Section>> listSection(){
		List<Section> sections = sectionService.list();
		return new ResponseEntity<>(sections, HttpStatus.OK);
	}

	@GetMapping(value = "/listCategory/{category}")
	@ResponseBody
	public ResponseEntity<List<SectionTypeDTO>> listSectionCategory(@Valid @PathVariable String category){
		List<SectionTypeDTO> sections = sectionService.findSectionCategories(category);
		return new ResponseEntity<>(sections, HttpStatus.OK);
	}

	@GetMapping(value = "/listProduct/{id}")
	@ResponseBody
	public ResponseEntity<WareHouseProductItensDTO> listProduct(@PathVariable Long id) {
		WareHouseProductItensDTO wareHouseList = sectionService.listProduct(id);
		return new ResponseEntity<>(wareHouseList, HttpStatus.OK);
	}

	@PutMapping(value = "/update/{id}")
	@ResponseBody
	public ResponseEntity<Section> updateSection(@Valid @RequestBody SectionDTO dto, @PathVariable Long id) throws Exception{
		try {
			Section section = sectionService.update(dto, id);
			return new ResponseEntity<>(section, HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
