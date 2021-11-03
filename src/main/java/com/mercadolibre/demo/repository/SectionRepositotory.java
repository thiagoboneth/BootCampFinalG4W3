package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepositotory extends JpaRepository<Section,Long> {

    @Query(value = "select u from Section  u where  upper(trim(u.category)) like %?1%")
    List<Section> buscarPorSessao(String name);
}
