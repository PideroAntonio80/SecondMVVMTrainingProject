package com.example.secondmvvmtrainingproject.network

import com.example.secondmvvmtrainingproject.model.PokemonDataResponse
import retrofit2.Response
import retrofit2.http.GET

/*
interface ApiService {
    @GET("pokedex.json")
    suspend fun getPokemons(): Response<List<PokemonDataModel>>
}*/
interface ApiService {
    @GET("pokedex.json")
    suspend fun getPokemons(): Response<PokemonDataResponse>
}