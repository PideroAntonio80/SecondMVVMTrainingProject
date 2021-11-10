package com.example.secondmvvmtrainingproject.repository

import com.example.secondmvvmtrainingproject.model.PokemonDataModel
import com.example.secondmvvmtrainingproject.network.RetrofitClient

class PokemonRepository {

    private val api = RetrofitClient()

    suspend fun getAllPokemon(): List<PokemonDataModel> {
        val response = api.getPokemons()

        return response?.pokemon ?: emptyList()
    }
}