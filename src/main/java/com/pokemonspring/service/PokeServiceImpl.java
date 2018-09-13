package com.pokemonspring.service;

import com.pokemonspring.model.Pokemon;
import com.pokemonspring.repository.PokemonTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokeServiceImpl implements PokeService {

    private final PokemonTeamRepository pokemonTeamRepository;

    @Autowired
    PokeServiceImpl(PokemonTeamRepository pokemonTeamRepository) {
        this.pokemonTeamRepository = pokemonTeamRepository;
    }

    public boolean add(Pokemon pokemon) {
        if (pokemonTeamRepository.currentTeamSize() < 6) {
            pokemonTeamRepository.saveAndFlush(pokemon);
            return true;
        }
        return false;
    }

    public void remove(int id) {
        pokemonTeamRepository.delete(id);
    }

    public List<Pokemon> findAll() {
        return pokemonTeamRepository.findAll();
    }
}
