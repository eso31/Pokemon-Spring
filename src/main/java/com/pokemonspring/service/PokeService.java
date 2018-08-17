package com.pokemonspring.service;

import com.pokemonspring.model.Pokemon;

import java.util.List;

public interface PokeService {
    void add(Pokemon pokemon);

    void remove(int id);

    List<Pokemon> findAll();
}
