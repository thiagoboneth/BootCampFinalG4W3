package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.model.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<Adress,Long>{

}
