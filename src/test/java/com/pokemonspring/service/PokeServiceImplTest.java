package com.pokemonspring.service;

import com.pokemonspring.model.Pokemon;
import com.pokemonspring.repository.PokemonTeamRepository;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
public class PokeServiceImplTest {

    private PokemonTeamRepository pokemonTeamRepository;
    private PokeService pokeService;

    @Before
    public void setUp() {
        pokemonTeamRepository = EasyMock.createMock(PokemonTeamRepository.class);
        pokeService = new PokeServiceImpl(pokemonTeamRepository);
    }

    @Test
    public void addWithNotFullTeamTest() {
        Pokemon pokemon = new Pokemon();
        EasyMock.expect(pokemonTeamRepository.currentTeamSize()).andReturn(5);
        EasyMock.expect(pokemonTeamRepository.saveAndFlush(pokemon)).andReturn(new Pokemon());
        EasyMock.replay(pokemonTeamRepository);

        pokeService.add(pokemon);
        EasyMock.verify(pokemonTeamRepository);
    }

    @Test
    public void addWithFullTeamTest() {
        Pokemon pokemon = new Pokemon();
        EasyMock.expect(pokemonTeamRepository.currentTeamSize()).andReturn(6);
        EasyMock.replay(pokemonTeamRepository);

        pokeService.add(pokemon);
        EasyMock.verify(pokemonTeamRepository);
    }

    @Test
    public void removeTest() {
        pokemonTeamRepository.delete(25);
        EasyMock.expectLastCall();
        EasyMock.replay(pokemonTeamRepository);

        pokeService.remove(25);
        EasyMock.verify(pokemonTeamRepository);
    }

    @Test
    public void findAllTest() {
        EasyMock.expect(pokemonTeamRepository.findAll()).andReturn(Collections.emptyList());
        EasyMock.replay(pokemonTeamRepository);

        pokeService.findAll();
        EasyMock.verify(pokemonTeamRepository);
    }
}