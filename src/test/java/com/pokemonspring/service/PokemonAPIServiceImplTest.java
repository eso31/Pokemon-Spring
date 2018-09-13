package com.pokemonspring.service;

import com.pokemonspring.model.Pokemon;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class PokemonAPIServiceImplTest {

    private PokemonAPIService pokemonAPIService;

    @Before
    public void setUp() {
        pokemonAPIService = new PokemonAPIServiceImpl();
    }

    @Test
    public void getPokemonTest() {
        Pokemon pokemon = pokemonAPIService.getPokemon(25);
        Pokemon expectedPokemon = new Pokemon(25, "pikachu", PokemonType.ELECTRIC, null
                , "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png");

        assertEquals(pokemon.getName(), expectedPokemon.getName());
    }
}