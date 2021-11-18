package com.example.secondmvvmtrainingproject.domain.usecase.pokemons

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity
import com.example.secondmvvmtrainingproject.domain.repository.IPokemonGameRepository

class PokemonGameMyTeamUseCase(private val repository: IPokemonGameRepository) {

    suspend fun getTeam(): ArrayList<PokemonEntity>? {
        return repository.getPokemonsTeam()
    }
}