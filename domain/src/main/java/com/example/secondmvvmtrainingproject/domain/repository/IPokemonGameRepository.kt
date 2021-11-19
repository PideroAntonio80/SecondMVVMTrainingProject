package com.example.secondmvvmtrainingproject.domain.repository

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntityGame

interface IPokemonGameRepository {
    suspend fun getPokemonsTeam(): ArrayList<PokemonEntityGame>?
    suspend fun getPokemonsEnemyTeam(): ArrayList<PokemonEntityGame>?
}