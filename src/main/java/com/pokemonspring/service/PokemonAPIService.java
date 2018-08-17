package com.pokemonspring.service;

import com.pokemonspring.model.Pokemon;

public interface PokemonAPIService {
    Pokemon getPokemon(Long id);

    Pokemon json2Pokemon(String json);

    String getPokemonFromAPI(Long id);
}
