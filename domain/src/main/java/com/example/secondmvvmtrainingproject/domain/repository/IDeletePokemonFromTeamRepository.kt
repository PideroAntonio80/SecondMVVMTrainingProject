package com.example.secondmvvmtrainingproject.domain.repository

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity

interface IDeletePokemonFromTeamRepository {
    suspend fun deletePokemonFromTeamRepository(pokemon: PokemonEntity): ArrayList<PokemonEntity>?
}