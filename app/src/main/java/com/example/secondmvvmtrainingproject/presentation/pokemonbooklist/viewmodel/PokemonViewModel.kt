package com.example.secondmvvmtrainingproject.presentation.pokemonbooklist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secondmvvmtrainingproject.data.repository.PokemonRepository
import com.example.secondmvvmtrainingproject.domain.model.common.Success
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonDataModel
import com.example.secondmvvmtrainingproject.domain.usecase.pokemons.GetAllPokemonsUseCase
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    val pokemonDataModel = MutableLiveData<ArrayList<PokemonDataModel>>()

    private val pokemonRepository = PokemonRepository()
    private val getAllPokemonsFeature = GetAllPokemonsUseCase(pokemonRepository)

    fun onCreate() {
        viewModelScope.launch {

            val result = getAllPokemonsFeature.build(null)

            if (result is Success) {
                pokemonDataModel.postValue(result.invoke())
            }
        }
    }
}




