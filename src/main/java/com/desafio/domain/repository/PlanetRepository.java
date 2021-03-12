package com.desafio.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.domain.model.Planet;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long>{

    Optional<Planet> findByName(String name);
    
    Page<Planet> findByName(Pageable pageable, String name);
    
    void detach(Planet planet);
	
}
