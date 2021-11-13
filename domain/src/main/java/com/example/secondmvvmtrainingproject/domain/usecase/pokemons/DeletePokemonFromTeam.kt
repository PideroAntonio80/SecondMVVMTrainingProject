package com.example.secondmvvmtrainingproject.domain.usecase.pokemons

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity
import com.example.secondmvvmtrainingproject.domain.repository.IDeletePokemonFromTeamRepository

class DeletePokemonFromTeam(private val repository: IDeletePokemonFromTeamRepository) {
    suspend fun getTeamAfterDeleting(pokemon: PokemonEntity): ArrayList<PokemonEntity>? {
        return repository.deletePokemonFromTeamRepository(pokemon)
    }
}