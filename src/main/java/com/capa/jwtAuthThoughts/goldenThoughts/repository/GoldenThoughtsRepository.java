package com.capa.jwtAuthThoughts.goldenThoughts.repository;

import com.capa.jwtAuthThoughts.goldenThoughts.entity.GoldenThought;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GoldenThoughtsRepository extends JpaRepository<GoldenThought, Integer> {

}
