package com.pokemonspring.model;

import com.pokemonspring.service.PokemonType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("unused")
@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int pokemonId;
    private String name;
    private PokemonType type1;
    private PokemonType type2;
    private String imageUrl;

    public Pokemon() {

    }

    public Pokemon(int pokemonId, String name, PokemonType type1, PokemonType type2, String imageUrl) {
        this.pokemonId = pokemonId;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PokemonType getType1() {
        return type1;
    }

    public void setType1(PokemonType type1) {
        this.type1 = type1;
    }

    public PokemonType getType2() {
        return type2;
    }

    public void setType2(PokemonType type2) {
        this.type2 = type2;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}