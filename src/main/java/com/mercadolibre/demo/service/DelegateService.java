package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.DelegateDTO;
import com.mercadolibre.demo.model.Delegate;
import com.mercadolibre.demo.model.Section;
import com.mercadolibre.demo.repository.DelegateRepository;
import com.mercadolibre.demo.repository.SectionRepositotory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DelegateService {

	@Autowired
	private DelegateRepository delegateRepository;
	@Autowired
	private SectionRepositotory sectionRepositotory;

	public Delegate save(DelegateDTO dto) throws Exception  {
		Delegate delegate;
		delegate = convertDelegateToDTO(dto);
		return delegateRepository.save(delegate);
	}

	public List<Delegate> list() {
		return delegateRepository.findAll();
	}

	public Optional<Delegate> findById(Long id) {
		return delegateRepository.findById(id);
	}

	public Delegate update(DelegateDTO dto, Long id) throws Exception {
		Delegate delegate = new Delegate();
		Optional<Delegate> existDelegate = findById(id);
		if(existDelegate.isPresent()) {
			delegate = convertDelegateToDTO(dto);
			delegate.setIdDelegate(id);
			return delegateRepository.saveAndFlush(delegate);
		} else {
			throw new Exception("Id não cadastrado");
		}
	}

	public void delete(Long id) {
		delegateRepository.deleteById(id);
	}

	public Delegate convertDelegateToDTO(DelegateDTO dto) throws Exception {
		Optional<Section> section = sectionRepositotory.findById(dto.getSectionCode());
		if(section.isPresent()) {
			return new Delegate(dto.getName(),dto.getLastname(), section.get());
		} else {
			throw new Exception("Id nao cadastrado");
		}
	}
}
