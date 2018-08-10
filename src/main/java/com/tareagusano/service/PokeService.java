package com.tareagusano.service;

import com.tareagusano.model.Pokemon;
import com.tareagusano.repository.PokeRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

@Service
public class PokeService {

    private PokeRepository _pokeRepository;

    @Autowired
    public PokeService(PokeRepository pokeRepository){
        _pokeRepository = pokeRepository;
    }

    public Pokemon getPokemon(Long id) throws IOException {
        String uri = "https://pokeapi.co/api/v2/pokemon/"+id+"/";

        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("User-Agent", null);

        InputStream inputStream = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String readLine = br.readLine();
        connection.disconnect();

        JSONObject jsonObject = new JSONObject(readLine);
        String name = (String) jsonObject.get("name");
        Long pokemon_id = new Long((int) jsonObject.get("id"));
        String imageUrl = (String) jsonObject.getJSONObject("sprites").get("front_default");
        JSONArray types = jsonObject.getJSONArray("types");
        String type1 = "";
        String type2 = "";

        if(types.length()<2){
            JSONObject obj = types.getJSONObject(0);
            type1 = (String) obj.getJSONObject("type").get("name");
        }else{
            JSONObject obj1 = types.getJSONObject(0);
            type1 = (String) obj1.getJSONObject("type").get("name");

            JSONObject obj2 = types.getJSONObject(1);
            type2 = (String) obj2.getJSONObject("type").get("name");
        }

        Pokemon pokemon = new Pokemon();
        pokemon.setPokemonId(pokemon_id);
        pokemon.setName(name);
        pokemon.setType1(type1);
        pokemon.setType2(type2);
        pokemon.setImageUrl(imageUrl);


        return pokemon;
    }

    public void add(Long id) throws IOException {
        if(_pokeRepository.findOne(id)==null && _pokeRepository.findAll().size()<=6) {
            Pokemon pokemon = getPokemon(id);
            _pokeRepository.saveAndFlush(pokemon);
        }
    }

    public void remove(Long id) {
        _pokeRepository.delete(id);
    }

    public List<Pokemon> findAll(){
        return _pokeRepository.findAll();
    }
}
