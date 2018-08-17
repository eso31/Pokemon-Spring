package com.pokemonspring.service;

import com.pokemonspring.model.Pokemon;

public interface PokemonAPIService {
    Pokemon getPokemon(int id);

    Pokemon json2Pokemon(String json);

    String getPokemonFromAPI(int id);
}
