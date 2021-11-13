package com.example.secondmvvmtrainingproject.domain.repository

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity

interface IPokemonTeamRepository {
    suspend fun getPokemonsTeam(): ArrayList<PokemonEntity>?
}