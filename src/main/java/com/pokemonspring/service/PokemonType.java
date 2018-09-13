package com.pokemonspring.service;

@SuppressWarnings("unused")
public enum PokemonType {
    NORMAL("NORMAL"),
    FIRE("FIRE"),
    WATER("WATER"),
    GRASS("GRASS"),
    ELECTRIC("ELECTRIC"),
    ICE("ICE"),
    FIGHTING("FIGHTING"),
    POISON("POISON"),
    GROUND("GROUND"),
    FLYING("FLYING"),
    PSYCHIC("PSYCHIC"),
    BUG("BUG"),
    ROCK("ROCK"),
    GHOST("GHOST"),
    DARK("DARK"),
    DRAGON("DRAGON"),
    STEEL("STEEL"),
    FAIRY("FAIRY");

    PokemonType(String name) {
    }

    @Override
    public String toString() {
        return this.name();
    }
}
