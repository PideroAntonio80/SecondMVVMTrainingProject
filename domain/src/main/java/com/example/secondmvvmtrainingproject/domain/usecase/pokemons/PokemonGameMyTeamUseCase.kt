package com.example.secondmvvmtrainingproject.domain.usecase.pokemons

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntityGame
import com.example.secondmvvmtrainingproject.domain.repository.IPokemonGameRepository

class PokemonGameMyTeamUseCase(private val repository: IPokemonGameRepository) {

    suspend fun getTeam(): ArrayList<PokemonEntityGame>? {
        return repository.getPokemonsTeam()
    }
}