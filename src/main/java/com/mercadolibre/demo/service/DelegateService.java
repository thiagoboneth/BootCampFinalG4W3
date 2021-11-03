package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.DelegateDTO;
import com.mercadolibre.demo.model.Delegate;
import com.mercadolibre.demo.model.Section;
import com.mercadolibre.demo.repository.DelegateRepository;
import com.mercadolibre.demo.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DelegateService {

    private DelegateRepository delegateRepository;
    private SectionRepository sectionRepository;

    @Autowired
    public DelegateService(DelegateRepository delegateRepository, SectionRepository sectionRepositotory) {
		this.delegateRepository = delegateRepository;
		this.sectionRepository = sectionRepositotory;
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

	public Optional<Section> getSection(DelegateDTO dto) throws Exception {
		Optional<Section> section = sectionRepository.findById(dto.getIdSection());
		return section;
	}
	
    public Delegate convertDelegateToDTO(DelegateDTO dto) throws Exception {
        if (getSection(dto).isPresent()) {
            return new Delegate(dto.getName(), dto.getLastname(), getSection(dto));
        } else {
            throw new Exception("Id não cadastrado");
        }
    }
}
