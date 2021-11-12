package com.example.secondmvvmtrainingproject.data.network.mapper.pokemons

import com.example.secondmvvmtrainingproject.data.commons.extensions.toArrayList
import com.example.secondmvvmtrainingproject.data.network.response.pokemons.getAllPokemons.GetAllPokemonsServiceResponse
import com.example.secondmvvmtrainingproject.data.network.response.pokemons.getAllPokemons.PokemonItem
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonDataModel

fun GetAllPokemonsServiceResponse.toDomain() : ArrayList<PokemonDataModel>{

    return this.pokemonList?.mapNotNull { it.toDomain() }.toArrayList() ?: ArrayList()
}

fun PokemonItem.toDomain() : PokemonDataModel? {

    return this.id?.let {

        PokemonDataModel(
            id = this.id,
            name = this.name ?: "",
            img = this.img ?: "",
            weight = this.weight ?: "",
            height = this.height ?: "",
            type = this.type.toArrayList() ?: ArrayList()
        )
    }
}