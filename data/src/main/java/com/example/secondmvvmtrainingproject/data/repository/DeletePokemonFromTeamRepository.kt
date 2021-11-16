package com.example.secondmvvmtrainingproject.data.repository

import com.example.secondmvvmtrainingproject.data.local.PokemonApplication
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity
import com.example.secondmvvmtrainingproject.domain.repository.IDeletePokemonFromTeamRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeletePokemonFromTeamRepository : IDeletePokemonFromTeamRepository {
    override suspend fun deletePokemonFromTeamRepository(pokemon: PokemonEntity): ArrayList<PokemonEntity>? {
        return withContext(Dispatchers.IO) {

            PokemonApplication.database.pokemonDao().deletePokemonFromTeam(pokemon)
            val pokemonsTeam = PokemonApplication.database.pokemonDao()
                .getPokemonTeam() as ArrayList<PokemonEntity>
            pokemonsTeam
        }
    }
}