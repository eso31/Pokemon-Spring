package com.pokemonspring.service;

import com.pokemonspring.model.Pokemon;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Service
public class PokemonAPIServiceImpl implements PokemonAPIService {
    public Pokemon getPokemon(Long id) {
        String jsonPokemon = getPokemonFromAPI(id);
        Pokemon pokemon = json2Pokemon(jsonPokemon);
        return pokemon;
    }

    public Pokemon json2Pokemon(String json){
        JSONObject jsonObject = new JSONObject(json);
        String name = (String) jsonObject.get("name");
        Long pokemon_id = (long) (int) jsonObject.get("id");
        String imageUrl = (String) jsonObject.getJSONObject("sprites").get("front_default");
        JSONArray types = jsonObject.getJSONArray("types");
        String type1;
        String type2 = "";

        if (types.length() < 2) {
            JSONObject obj = types.getJSONObject(0);
            type1 = (String) obj.getJSONObject("type").get("name");
            type1 = PokemonType.valueOf(type1.toUpperCase()).toString();
        } else {
            JSONObject obj1 = types.getJSONObject(0);
            type1 = (String) obj1.getJSONObject("type").get("name");
            type1 = PokemonType.valueOf(type1.toUpperCase()).toString();

            JSONObject obj2 = types.getJSONObject(1);
            type2 = (String) obj2.getJSONObject("type").get("name");
            type2 = PokemonType.valueOf(type2.toUpperCase()).toString();
        }

        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonId(pokemon_id);
        pokemon.setName(name);
        pokemon.setType1(type1);
        pokemon.setType2(type2);
        pokemon.setImageUrl(imageUrl);

        return pokemon;
    }

    public String getPokemonFromAPI(Long id) {
        String uri = "https://pokeapi.co/api/v2/pokemon/" + id + "/";

        URL url = null;
        try {
            url = new URL(uri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("User-Agent", null);

        InputStream inputStream;
        try {
            inputStream = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String readLine = null;
        try {
            readLine = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.disconnect();

        return readLine;
    }
}
