package com.example.secondmvvmtrainingproject.domain.usecase.pokemons

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonDataModel
import com.example.secondmvvmtrainingproject.domain.repository.IAddPokemonTeamRepository
import com.example.secondmvvmtrainingproject.domain.utils.MyResponse

class AddPokemonToTeam(private val repository: IAddPokemonTeamRepository) {

    suspend fun addPokemon(pokemon: PokemonDataModel) : MyResponse? = repository.addPokemonToTeam(pokemon)
}