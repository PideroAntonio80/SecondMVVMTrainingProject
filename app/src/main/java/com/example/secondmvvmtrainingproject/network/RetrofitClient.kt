package com.example.secondmvvmtrainingproject.network

import com.example.secondmvvmtrainingproject.core.RetrofitProvider
import com.example.secondmvvmtrainingproject.model.PokemonDataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrofitClient {

    private val retrofit = RetrofitProvider.getRetrofit()

    /*suspend fun getPokemons(): List<PokemonDataModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiService::class.java).getPokemons()
            *//* Aquí debajo, response me devolvería una lista de pokemon, y con el operador elvis
            "?:" le digo que si la lista que me devuelve es nula, me de una lista vacía*//*
            response.body() ?: emptyList()
        }
    }*/

    suspend fun getPokemons(): PokemonDataResponse? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiService::class.java).getPokemons()
            response.body()
        }
    }
}