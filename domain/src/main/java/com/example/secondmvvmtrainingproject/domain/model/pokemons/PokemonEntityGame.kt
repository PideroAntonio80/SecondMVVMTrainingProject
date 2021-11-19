package com.example.secondmvvmtrainingproject.domain.model.pokemons

data class PokemonEntityGame(val id: Int,
                             val name: String,
                             val img: String,
                             val weight: String,
                             val height: String,
                             val type: String,
                             var victory: Boolean)
