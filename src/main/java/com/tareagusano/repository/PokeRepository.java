package com.tareagusano.repository;

import com.tareagusano.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokeRepository extends JpaRepository<Pokemon, Long> {
}
