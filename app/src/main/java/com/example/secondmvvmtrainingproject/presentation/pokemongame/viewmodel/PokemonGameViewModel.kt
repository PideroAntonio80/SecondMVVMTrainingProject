package com.example.secondmvvmtrainingproject.presentation.pokemongame.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secondmvvmtrainingproject.data.repository.PokemonGameRepository
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntityGame
import com.example.secondmvvmtrainingproject.domain.usecase.pokemons.PokemonGameEnemyTeamUseCase
import com.example.secondmvvmtrainingproject.domain.usecase.pokemons.PokemonGameMyTeamUseCase
import kotlinx.coroutines.launch

class PokemonGameViewModel : ViewModel() {

    val pokemonGameMyTeam = MutableLiveData<ArrayList<PokemonEntityGame>?>()
    val pokemonGameEnemyTeam = MutableLiveData<ArrayList<PokemonEntityGame>?>()

    private val pokemonGameRepository = PokemonGameRepository()

    fun onCreate() {
        viewModelScope.launch {
            val myTeam = PokemonGameMyTeamUseCase(pokemonGameRepository).getTeam() ?: ArrayList()

            if (!myTeam.isNullOrEmpty()) {
                pokemonGameMyTeam.postValue(myTeam)
            }
        }
    }

    fun setEnemyTeam() {
        viewModelScope.launch {
            val enemyTeam = PokemonGameEnemyTeamUseCase(pokemonGameRepository).getEnemyTeam() ?: ArrayList()

            if (!enemyTeam.isNullOrEmpty()) {
                pokemonGameEnemyTeam.postValue(enemyTeam)
            }
        }
    }
}