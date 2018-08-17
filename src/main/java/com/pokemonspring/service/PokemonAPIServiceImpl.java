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

    private final static String pokeapiURL = "https://pokeapi.co/api/v2/pokemon/";

    public Pokemon getPokemon(int id) {
        String jsonPokemon = getPokemonFromAPI(id);
        Pokemon pokemon = json2Pokemon(jsonPokemon);
        return pokemon;
    }

    public Pokemon json2Pokemon(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String name = (String) jsonObject.get("name");
        int pokemonId = jsonObject.getInt("id");
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

        return new Pokemon(pokemonId, name, type1, type2, imageUrl);
    }

    public String getPokemonFromAPI(int id) {
        String uri = pokeapiURL + id;

        URL url = null;
        try {
            url = new URL(uri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = null;
        try {
            if (url != null) {
                connection = (HttpURLConnection) url.openConnection();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.setRequestMethod("GET");
            }
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
