package com.example.secondmvvmtrainingproject.data.local.mapper

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntityGame

fun pokemonMapperToEntityGame(pokemonEntity: PokemonEntity) : PokemonEntityGame {

    return PokemonEntityGame(id = pokemonEntity.id,
        name = pokemonEntity.name,
        img = pokemonEntity.img,
        weight = pokemonEntity.weight,
        height = pokemonEntity.height,
        type = pokemonEntity.type,
        victory = false
    )
}