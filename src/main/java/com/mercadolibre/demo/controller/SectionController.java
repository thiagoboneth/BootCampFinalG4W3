package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.dto.SectionDTO;
import com.mercadolibre.demo.model.Section;
import com.mercadolibre.demo.repository.SectionRepositotory;
import com.mercadolibre.demo.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/section")
public class SectionController {

	@Autowired
	private SectionService sectionService;

	@Autowired
	private SectionRepositotory sectionRepositotory;


	@PostMapping(value = "/save")
	public ResponseEntity<Section> saveSection(@Valid @RequestBody SectionDTO dto) throws Exception{
		try {
			Section section = sectionService.save(dto);
			return new ResponseEntity<>(section, HttpStatus.CREATED);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/list")
	@ResponseBody
	public ResponseEntity<List<Section>> listSection(){
		List<Section> sections = sectionService.list();
		return new ResponseEntity<>(sections, HttpStatus.OK);
	}

	@GetMapping(value = "/listCategory")
	@ResponseBody
	public ResponseEntity<List<Section>> listSectionCategory(@RequestParam(name = "name") String name){
		List<Section> sections = sectionRepositotory.buscarPorSessao(name.trim().toUpperCase(Locale.ROOT));
		return new ResponseEntity<>(sections, HttpStatus.OK);
	}

	@PutMapping(value = "/update/{id}")
	@ResponseBody
	public ResponseEntity<Section> updateSection(@RequestBody SectionDTO dto, @PathVariable Long id) throws Exception{
		try {
			Section section = sectionService.update(dto, id);
			return new ResponseEntity<>(section, HttpStatus.CREATED);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity<String> deleteSection(@RequestParam Long id){
		try {
			sectionService.delete(id);
			return new ResponseEntity<>("Secao deletada com sucesso", HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
