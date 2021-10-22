package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.model.Delegate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelegateRepository extends JpaRepository<Delegate, Long> {
}
