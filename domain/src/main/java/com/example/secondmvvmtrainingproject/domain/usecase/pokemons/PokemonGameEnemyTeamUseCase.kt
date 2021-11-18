package com.example.secondmvvmtrainingproject.domain.usecase.pokemons

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity
import com.example.secondmvvmtrainingproject.domain.repository.IPokemonGameRepository

class PokemonGameEnemyTeamUseCase(private val repository: IPokemonGameRepository) {

    suspend fun getEnemyTeam(): ArrayList<PokemonEntity>? {
        return repository.getPokemonsEnemyTeam()
    }
}