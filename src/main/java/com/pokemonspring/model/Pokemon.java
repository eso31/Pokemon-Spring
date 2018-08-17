package com.pokemonspring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pokemon{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private int pokemonId;
    private String name;
    private String type1;
    private String type2;
    private String imageUrl;

    public Pokemon(){

    }

    public Pokemon(int pokemonId, String name, String type1, String type2, String imageUrl) {
        this.pokemonId = pokemonId;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return Id;
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

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}