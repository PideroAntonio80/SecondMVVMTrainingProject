package com.example.secondmvvmtrainingproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secondmvvmtrainingproject.domain.GetAllPokemonsFeature
import com.example.secondmvvmtrainingproject.model.PokemonDataModel
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    val pokemonDataModel = MutableLiveData<List<PokemonDataModel>>()

    var getAllPokemonsFeature = GetAllPokemonsFeature()

    fun onCreate() {
        viewModelScope.launch {
            val result = getAllPokemonsFeature()

            if (!result.isNullOrEmpty()) {
                pokemonDataModel.postValue(result)
            }
        }
    }
}