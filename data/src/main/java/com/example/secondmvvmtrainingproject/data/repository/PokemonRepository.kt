package com.example.secondmvvmtrainingproject.data.repository

import com.example.secondmvvmtrainingproject.data.network.mapper.pokemons.toDomain
import com.example.secondmvvmtrainingproject.data.network.service.PokemonService
import com.example.secondmvvmtrainingproject.data.network.service.base.RetrofitApiClientGenerator
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonDataModel
import com.example.secondmvvmtrainingproject.domain.repository.IPokemonRepository

class PokemonRepository : IPokemonRepository {

    private val api = RetrofitApiClientGenerator()

    override suspend fun getAllPokemon(): ArrayList<PokemonDataModel> {
        val api = api.generateApi(PokemonService::class.java)
        val response = api.getAllPokemons()

        return response.toDomain()
    }
}