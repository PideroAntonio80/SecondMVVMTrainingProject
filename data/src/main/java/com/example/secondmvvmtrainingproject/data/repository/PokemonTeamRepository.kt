package com.example.secondmvvmtrainingproject.data.repository

import com.example.secondmvvmtrainingproject.data.local.PokemonApplication
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity
import com.example.secondmvvmtrainingproject.domain.repository.IPokemonTeamRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonTeamRepository: IPokemonTeamRepository {

    override suspend fun getPokemonsTeam(): ArrayList<PokemonEntity>? {

        return withContext(Dispatchers.IO) {
            val pokemonsTeam = PokemonApplication.database.pokemonDao()
                .getPokemonTeam() as ArrayList<PokemonEntity>
            pokemonsTeam
        }
    }
}







//    NOTAS PARA REACORDAR: En una hipotética función como la de debajo...
//    ---------------------
//    private fun getTeam() {
//        doAsync {
//            val myPokemons = PokemonApplication.database.pokemonDao().getPokemonTeam() as ArrayList<PokemonEntity>
//            uiThread {
//                pokemons = myPokemons // TODO -> EL PROBLEMA ES QUE AL SALIR DEL HILO SECUNDARIO, "myPokemons" NO LE PASA SU VALOR A "pokemons"
//            }                         // TODO -> POR LO QUE "myPokemons" = lista de pokemons Y "pokemons" = null
//        }                             // TODO -> ¿¿¿¿¿¿¿ CÓMO REMEDIAR ESTO????????? ---> USANDO CORRUTINAS
//    }
