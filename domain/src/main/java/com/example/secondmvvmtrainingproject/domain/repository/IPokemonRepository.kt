package com.example.secondmvvmtrainingproject.domain.repository

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonDataModel

interface IPokemonRepository {
    suspend fun getAllPokemon(): ArrayList<PokemonDataModel>
}