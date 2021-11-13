package com.example.secondmvvmtrainingproject.presentation.pokemonteam.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secondmvvmtrainingproject.data.repository.DeletePokemonFromTeamRepository
import com.example.secondmvvmtrainingproject.data.repository.PokemonTeamRepository
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity
import com.example.secondmvvmtrainingproject.domain.usecase.pokemons.DeletePokemonFromTeam
import com.example.secondmvvmtrainingproject.domain.usecase.pokemons.GetPokemonsTeam
import kotlinx.coroutines.launch

class PokemonTeamViewModel : ViewModel() {

    val pokemonTeam = MutableLiveData<ArrayList<PokemonEntity>?>()

    private val pokemonTeamRepository = PokemonTeamRepository()
    private val deletedPokemonFromTeamRepository = DeletePokemonFromTeamRepository()

    private lateinit var myTeam: ArrayList<PokemonEntity>
    private lateinit var myTeamAfterDeleting: ArrayList<PokemonEntity>

    fun onCreate() {
        viewModelScope.launch {
            getPokemonsTeam()
            val result = myTeam
            if(!result.isNullOrEmpty()) {
                pokemonTeam.postValue(result)
            }
        }
    }

    private suspend fun getPokemonsTeam() : ArrayList<PokemonEntity> {
        myTeam = GetPokemonsTeam(pokemonTeamRepository).getTeam()!!
        return myTeam
    }

    suspend fun deletePokemon(pokemon: PokemonEntity) : ArrayList<PokemonEntity> {
        myTeamAfterDeleting = DeletePokemonFromTeam(deletedPokemonFromTeamRepository).getTeamAfterDeleting(pokemon)!!
        return myTeamAfterDeleting
    }

    fun getListAfterDeletingPokemon(pokemon: PokemonEntity) {
        viewModelScope.launch {
            deletePokemon(pokemon)
            val result = myTeamAfterDeleting
            if(!result.isNullOrEmpty()) {
                pokemonTeam.postValue(result)
            }
        }
    }
}

/* fun addPokemon(pokemon: PokemonEntity) {
        if (!pokemons.contains(pokemon)) {
            pokemons.add(pokemon)
        }
    }
-------------------------------------------
Para el delete:
    val index = myTeam.indexOf(pokemon)
        if (index != -1) {
            myTeam.removeAt(index)
        } */