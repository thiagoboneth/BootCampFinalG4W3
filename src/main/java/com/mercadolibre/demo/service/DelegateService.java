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

    private DelegateRepository delegateRepository;
    private SectionRepositotory sectionRepositotory;

    @Autowired
    public DelegateService(DelegateRepository delegateRepository, SectionRepositotory sectionRepositotory) {
		this.delegateRepository = delegateRepository;
		this.sectionRepositotory = sectionRepositotory;
	}
    

	public Delegate save(DelegateDTO dto) throws Exception {
        Delegate delegate = convertDelegateToDTO(dto);
        return delegateRepository.save(delegate);
    }

    public List<Delegate> list() {
        return delegateRepository.findAll();
    }

    public Optional<Delegate> findById(Long id) {
        return delegateRepository.findById(id);
    }

    public Delegate update(DelegateDTO dto, Long id) throws Exception {
        Optional<Delegate> existDelegate = findById(id);
        if (existDelegate.isPresent()) {
        	Delegate delegate = convertDelegateToDTO(dto);
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
        if (section.isPresent()) {
            return new Delegate(dto.getName(), dto.getLastname(), section.get());
        } else {
            throw new Exception("Id nao cadastrado");
        }
    }
}
