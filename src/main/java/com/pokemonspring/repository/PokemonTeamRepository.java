package com.pokemonspring.repository;

import com.pokemonspring.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonTeamRepository extends JpaRepository<Pokemon, Integer> {
//    int currentTeamSize();
}
