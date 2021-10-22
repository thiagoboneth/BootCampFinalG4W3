package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepositotory extends JpaRepository<Section,Long> {
}
