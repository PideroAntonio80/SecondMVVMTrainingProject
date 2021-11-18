package com.example.secondmvvmtrainingproject.domain.repository

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity

interface IPokemonGameRepository {
    suspend fun getPokemonsTeam(): ArrayList<PokemonEntity>?
    suspend fun getPokemonsEnemyTeam(): ArrayList<PokemonEntity>?
}