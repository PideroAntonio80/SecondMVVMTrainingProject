package com.example.secondmvvmtrainingproject.domain.usecase.pokemons

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity
import com.example.secondmvvmtrainingproject.domain.repository.IPokemonTeamRepository

class GetPokemonsTeam(private val repository: IPokemonTeamRepository) {

    suspend fun getTeam(): ArrayList<PokemonEntity>? {
        return repository.getPokemonsTeam()
    }
}