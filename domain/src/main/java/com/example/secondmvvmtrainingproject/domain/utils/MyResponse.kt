package com.example.secondmvvmtrainingproject.domain.utils

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity

data class MyResponse(var teamList : ArrayList<PokemonEntity>?, var message: String)