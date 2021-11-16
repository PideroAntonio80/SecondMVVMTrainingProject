package com.example.secondmvvmtrainingproject.data.local.mapper

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonDataModel
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity

fun pokemonMapperToEntity(pokemonDataModel: PokemonDataModel) : PokemonEntity {

    return PokemonEntity(id = pokemonDataModel.id,
        name = pokemonDataModel.name ?: "",
        img = pokemonDataModel.img ?: "",
        weight = pokemonDataModel.weight ?: "",
        height = pokemonDataModel.height ?: "",
        type = pokemonDataModel.type!![0]
    )
}