package com.pokemonspring.service;

import com.pokemonspring.model.Pokemon;
import com.pokemonspring.repository.PokemonTeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokeServiceImpl implements PokeService{

    private final PokemonTeamRepository pokemonTeamRepository;

    public PokeServiceImpl(PokemonTeamRepository pokemonTeamRepository) {
        this.pokemonTeamRepository = pokemonTeamRepository;
    }

    public void add(Pokemon pokemon) {
        int teamSize = pokemonTeamRepository.findAll().size();
        boolean existsInDB = pokemonTeamRepository.exists(pokemon.getPokemonId());

        if (!existsInDB && teamSize < 6) {
            pokemonTeamRepository.saveAndFlush(pokemon);
        }
    }

    public void remove(Long id) {
        pokemonTeamRepository.delete(id);
    }

    public List<Pokemon> findAll() {
        return pokemonTeamRepository.findAll();
    }
}
