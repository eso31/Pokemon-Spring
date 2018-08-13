package com.tareagusano.controller;

import com.tareagusano.model.Pokemon;
import com.tareagusano.service.PokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.Path;
import java.io.IOException;
import java.util.List;

@Controller
public class PokeController {

    private PokeService _pokeService;

    @Autowired
    public PokeController(PokeService pokeService) {
        _pokeService = pokeService;
    }

    @RequestMapping(value = "/pokesearch", method = RequestMethod.GET)
    public String pokeapi() {
        return "search";
    }

    @RequestMapping(value = "/pokesearch", method = RequestMethod.POST)
    public ModelAndView pokeapi(Long id) throws IOException {
        return new ModelAndView("add", "pokemon", _pokeService.getPokemon(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPokemon(Pokemon pokemon) throws IOException {
        _pokeService.add(pokemon);
        return new ModelAndView("redirect:/myteam");
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ModelAndView removePokemon(Long id) throws IOException {
        _pokeService.remove(id);
        return new ModelAndView("redirect:/myteam");
    }

    @RequestMapping("/myteam")
    public ModelAndView getTeam() {
        return new ModelAndView("myteam", "pokemonList", _pokeService.findAll());
    }
}
