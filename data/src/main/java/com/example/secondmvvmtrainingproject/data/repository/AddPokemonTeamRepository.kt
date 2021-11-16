package com.example.secondmvvmtrainingproject.data.repository

import com.example.secondmvvmtrainingproject.data.local.PokemonApplication
import com.example.secondmvvmtrainingproject.data.local.mapper.pokemonMapperToEntity
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonDataModel
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity
import com.example.secondmvvmtrainingproject.domain.repository.IAddPokemonTeamRepository
import com.example.secondmvvmtrainingproject.domain.utils.MyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddPokemonTeamRepository : IAddPokemonTeamRepository {

    private var myNewTeam: ArrayList<PokemonEntity>? = null
    private lateinit var message: String
    private var response: MyResponse? = null
    private var repeat = false

    override suspend fun addPokemonToTeam(pokemon: PokemonDataModel): MyResponse {

        return withContext(Dispatchers.IO) {

            val pokemonsTeam = PokemonApplication.database.pokemonDao().getPokemonTeam() as ArrayList<PokemonEntity>

            if (pokemonsTeam.size >= 3) {
                message = "Equipo completo"
                myNewTeam = pokemonsTeam
                response = MyResponse(myNewTeam, message)
            } else {

                for(pok in pokemonsTeam) {
                    if (pok.id == pokemon.id) {
                        repeat = true
                    }
                }

                if (repeat) {
                    message = "Pokemon repetido"
                    myNewTeam = pokemonsTeam
                    response = MyResponse(myNewTeam, message)
                }
                else {
                    message = "Pokemon añadido"
                    PokemonApplication.database.pokemonDao().addPokemonToTeam(pokemonMapperToEntity(pokemon))
                    myNewTeam = PokemonApplication.database.pokemonDao().getPokemonTeam() as ArrayList<PokemonEntity>
                    response = MyResponse(myNewTeam, message)
                }
            }
            response!!
        }
    }
}