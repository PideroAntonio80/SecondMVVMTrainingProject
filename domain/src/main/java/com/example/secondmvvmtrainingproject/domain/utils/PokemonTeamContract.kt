package com.example.secondmvvmtrainingproject.domain.utils

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity

interface PokemonTeamContract {

    interface ViewModel {
        suspend fun success (pokemonTeamList: ArrayList<PokemonEntity>?)
        suspend fun reject (message: String)
    }

    interface UseCase {
        suspend fun success (pokemonTeamList: ArrayList<PokemonEntity>?)
        suspend fun reject (message: String)
    }

    interface Repository {
        suspend fun getNewTeam(onPokemonTeamListener: OnPokemonTeamListener)
        interface OnPokemonTeamListener {
            suspend fun success (pokemonTeamList: ArrayList<PokemonEntity>?)
            suspend fun reject (message: String)
        }
    }
}