package com.example.secondmvvmtrainingproject.data.repository

import com.example.secondmvvmtrainingproject.data.local.PokemonApplication
import com.example.secondmvvmtrainingproject.data.local.mapper.pokemonMapperToEntityGame
import com.example.secondmvvmtrainingproject.data.local.mapper.pokemonMapperToEntity
import com.example.secondmvvmtrainingproject.data.network.mapper.pokemons.toDomain
import com.example.secondmvvmtrainingproject.data.network.service.PokemonService
import com.example.secondmvvmtrainingproject.data.network.service.base.RetrofitApiClientGenerator
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntityGame
import com.example.secondmvvmtrainingproject.domain.repository.IPokemonGameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonGameRepository: IPokemonGameRepository {

    private val api = RetrofitApiClientGenerator()

    private var n = 0
    private var n2 = 0

    override suspend fun getPokemonsTeam(): ArrayList<PokemonEntityGame> {
        val myTeam = arrayListOf<PokemonEntityGame>()
        return withContext(Dispatchers.IO) {
            val pokemonsTeam = PokemonApplication.database.pokemonDao()
                .getPokemonTeam() as ArrayList<PokemonEntity>

            for (pok in pokemonsTeam) {
                myTeam.add(pokemonMapperToEntityGame(pok))
            }

            myTeam
        }
    }

    override suspend fun getPokemonsEnemyTeam(): ArrayList<PokemonEntityGame> {

        val api = api.generateApi(PokemonService::class.java)
        val response = api.getAllPokemons()

        val enemyTeam: ArrayList<PokemonEntity> = ArrayList()
        val enemyRealTeam: ArrayList<PokemonEntityGame> = ArrayList()
        val rand1 = ( 0..150 ).random()

        do { n = (0 .. 150).random() }
        while (n == rand1)
        val rand2 = n

        do { n2 = (0 .. 150).random() }
        while (n2 == rand1 || n2 == rand2)
        val rand3 = n2

        val selectThree = arrayListOf(response.toDomain()[rand1], response.toDomain()[rand2], response.toDomain()[rand3])

        for (pokemon in selectThree) {
            enemyTeam.add(pokemonMapperToEntity(pokemon))
        }

        for (pokemon in enemyTeam) {
            enemyRealTeam.add(pokemonMapperToEntityGame(pokemon))
        }

        return enemyRealTeam
    }
}



