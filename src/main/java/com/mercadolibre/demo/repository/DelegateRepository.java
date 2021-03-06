package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.model.Delegate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DelegateRepository extends JpaRepository<Delegate, Long> {
	
}
