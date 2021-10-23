package com.mercadolibre.demo.service;

import com.mercadolibre.demo.model.Delegate;
import com.mercadolibre.demo.model.Seller;
import com.mercadolibre.demo.repository.DelegateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DelegateService {

    private DelegateRepository delegateRepository;

    @Autowired
    public DelegateService(DelegateRepository delegateRepository) {
        this.delegateRepository = delegateRepository;
    }

    public Delegate save(Delegate delegate) {
        return delegateRepository.save(delegate);
    }
    public List<Delegate> list() {return delegateRepository.findAll();}
    public Delegate update(Delegate delegate) {
        return delegateRepository.saveAndFlush(delegate);
    }
    public void delete(Long id) {
        delegateRepository.deleteById(id);
    }
}
