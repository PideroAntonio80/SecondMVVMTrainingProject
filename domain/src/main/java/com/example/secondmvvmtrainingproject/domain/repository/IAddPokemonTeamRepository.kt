package com.example.secondmvvmtrainingproject.domain.repository

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonDataModel
import com.example.secondmvvmtrainingproject.domain.utils.MyResponse

interface IAddPokemonTeamRepository {
    suspend fun addPokemonToTeam(pokemon: PokemonDataModel): MyResponse?
}