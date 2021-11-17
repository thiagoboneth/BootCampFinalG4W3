package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.dto.SectionCategoryDTO;
import com.mercadolibre.demo.dto.SectionTypeDTO;
import com.mercadolibre.demo.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section,Long> {


    @Query(nativeQuery = true, value = "SELECT s.* from  section s where upper(trim(s.category)) like %?1%")
    List<Section> buscarPorSessao(String name);

    List<Section> findByCategoryContaining(String param);

}