package com.example.secondmvvmtrainingproject.domain.usecase.pokemons

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonDataModel
import com.example.secondmvvmtrainingproject.domain.model.common.AppResult
import com.example.secondmvvmtrainingproject.domain.model.common.Success
import com.example.secondmvvmtrainingproject.domain.repository.IPokemonRepository
import com.example.secondmvvmtrainingproject.domain.usecase.base.BaseCoroutinesUseCase
import com.example.secondmvvmtrainingproject.domain.usecase.base.UseCaseParams

class GetAllPokemonsUseCase(private val repository: IPokemonRepository) :
    BaseCoroutinesUseCase<ArrayList<PokemonDataModel>, UseCaseParams?>() {

    override suspend fun buildUseCase(params: UseCaseParams?): AppResult<ArrayList<PokemonDataModel>> {

        // Obtain the data
        val list = repository.getAllPokemon()

        return Success(
            list
        )
    }
}