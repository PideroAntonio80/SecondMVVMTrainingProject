package com.example.secondmvvmtrainingproject.data.local.mapper

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntityD

fun pokemonMapperToEntityD(pokemonList: ArrayList<PokemonEntity>) : ArrayList<PokemonEntityD> {

    lateinit var pokemonTeamDList : ArrayList<PokemonEntityD>

    for (pokemon in pokemonList) {

        val pokemonTeamD = PokemonEntityD(id = pokemon.id,
            name = pokemon.name ?: "",
            img = pokemon.img ?: "",
            weight = pokemon.weight ?: "",
            height = pokemon.height ?: "",
            type = pokemon.type ?: "")

        pokemonTeamDList.add(pokemonTeamD)
    }
    return pokemonTeamDList
}

fun pokemonToPokemonD(pokemon: PokemonEntity) : PokemonEntityD {
    return PokemonEntityD(id = pokemon.id,
        name = pokemon.name ?: "",
        img = pokemon.img ?: "",
        weight = pokemon.weight ?: "",
        height = pokemon.height ?: "",
        type = pokemon.type ?: "")
}