package com.example.secondmvvmtrainingproject.data.network.service

import com.example.secondmvvmtrainingproject.data.network.response.pokemons.getAllPokemons.GetAllPokemonsServiceResponse
import retrofit2.http.GET

interface PokemonService {
    @GET("pokedex.json")
    suspend fun getAllPokemons(): GetAllPokemonsServiceResponse
}