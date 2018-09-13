package com.pokemonspring.controller;

import com.pokemonspring.model.Pokemon;
import com.pokemonspring.service.PokeService;
import com.pokemonspring.service.PokemonAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PokeController {

    private PokeService pokeService;
    private PokemonAPIService apiService;

    @Autowired
    public PokeController(PokeService pokeService, PokemonAPIService apiService) {
        this.pokeService = pokeService;
        this.apiService = apiService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView root() {
        return new ModelAndView("redirect:/pokesearch");
    }

    @RequestMapping(value = "/pokesearch", method = RequestMethod.GET)
    public ModelAndView pokeapi() {
        return new ModelAndView("search");
    }

    @RequestMapping(value = "/pokesearch", method = RequestMethod.POST)
    public ModelAndView pokeapi(int id) {
        return new ModelAndView("add", "pokemon", apiService.getPokemon(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPokemon(Pokemon pokemon, RedirectAttributes redirectAttributes) {
        if(!pokeService.add(pokemon))
            redirectAttributes.addFlashAttribute("message", "Pokemon team is full. "+ StringUtils.capitalize(pokemon.getName()) + " was not added.");
        return new ModelAndView("redirect:/myteam");
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ModelAndView removePokemon(int id) {
        pokeService.remove(id);
        return new ModelAndView("redirect:/myteam");
    }

    @RequestMapping("/myteam")
    public ModelAndView getTeam() {
        return new ModelAndView("myteam", "pokemonList", pokeService.findAll());
    }
}
