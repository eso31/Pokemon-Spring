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
import java.net.URL;

@Service
public class PokemonAPIServiceImpl implements PokemonAPIService {

    private final static String POKEAPI_URL = "https://pokeapi.co/api/v2/pokemon/";

    public Pokemon getPokemon(int id) {
        String jsonPokemon = getPokemonFromAPI(id);
        return json2Pokemon(jsonPokemon);
    }

    public Pokemon json2Pokemon(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String pokemonName = (String) jsonObject.get("name");
        int pokemonId = jsonObject.getInt("id");
        String imageUrl = (String) jsonObject.getJSONObject("sprites").get("front_default");
        JSONArray types = jsonObject.getJSONArray("types");
        PokemonType type1 = createPokemonType(types.getJSONObject(0));
        PokemonType type2 = getType2IfRequired(types);

        return new Pokemon(pokemonId, pokemonName, type1, type2, imageUrl);
    }

    private PokemonType getType2IfRequired(JSONArray types) {
        if (types.length() > 1) {
            return createPokemonType(types.getJSONObject(1));
        }

        return null;
    }

    private PokemonType createPokemonType(JSONObject type){
        String typeName = (String) type.getJSONObject("type").get("name");
        return PokemonType.valueOf(typeName.toUpperCase());
    }

    public String getPokemonFromAPI(int id) {
        String uri = POKEAPI_URL + id;

        URL url;
        HttpURLConnection connection;
        InputStream inputStream;
        BufferedReader bufferedReader;
        String readLine = "";

        try {
            url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("User-Agent", null);

            inputStream = connection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            readLine = bufferedReader.readLine();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return readLine;
    }
}
