package com.pokemonspring.controller;

import com.pokemonspring.model.Pokemon;
import com.pokemonspring.service.PokemonAPIService;
import com.pokemonspring.service.PokeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PokeController {

    private PokeServiceImpl pokeServiceImpl;
    private PokemonAPIService apiService;

    public PokeController(PokeServiceImpl pokeServiceImpl, PokemonAPIService apiService) {
        this.pokeServiceImpl = pokeServiceImpl;
        this.apiService = apiService;
    }

    @RequestMapping(value = "/pokesearch", method = RequestMethod.GET)
    public String pokeapi() {
        return "search";
    }

    @RequestMapping(value = "/pokesearch", method = RequestMethod.POST)
    public ModelAndView pokeapi(Long id) {
        return new ModelAndView("add", "pokemon", apiService.getPokemon(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPokemon(Pokemon pokemon) {
        pokeServiceImpl.add(pokemon);
        return new ModelAndView("redirect:/myteam");
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ModelAndView removePokemon(Long id) {
        pokeServiceImpl.remove(id);
        return new ModelAndView("redirect:/myteam");
    }

    @RequestMapping("/myteam")
    public ModelAndView getTeam() {
        return new ModelAndView("myteam", "pokemonList", pokeServiceImpl.findAll());
    }
}
