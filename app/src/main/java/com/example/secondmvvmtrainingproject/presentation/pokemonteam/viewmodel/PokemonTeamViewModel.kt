package com.example.secondmvvmtrainingproject.presentation.pokemonteam.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secondmvvmtrainingproject.data.repository.AddPokemonTeamRepository
import com.example.secondmvvmtrainingproject.data.repository.DeletePokemonFromTeamRepository
import com.example.secondmvvmtrainingproject.data.repository.PokemonTeamRepository
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonDataModel
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity
import com.example.secondmvvmtrainingproject.domain.usecase.pokemons.AddPokemonToTeam
import com.example.secondmvvmtrainingproject.domain.usecase.pokemons.DeletePokemonFromTeam
import com.example.secondmvvmtrainingproject.domain.usecase.pokemons.GetPokemonsTeam
import kotlinx.coroutines.launch

class PokemonTeamViewModel : ViewModel() {

    val pokemonTeam = MutableLiveData<ArrayList<PokemonEntity>?>()
    var message = MutableLiveData<String>()

    private val pokemonTeamRepository = PokemonTeamRepository()

    private val addPokemontoTeamRepository = AddPokemonTeamRepository()
    private var addPokemonToTeam = AddPokemonToTeam(addPokemontoTeamRepository)

    private val deletedPokemonFromTeamRepository = DeletePokemonFromTeamRepository()

    fun onCreate() {
        viewModelScope.launch {
            val myTeam = GetPokemonsTeam(pokemonTeamRepository).getTeam() ?: ArrayList()
            if(!myTeam.isNullOrEmpty()) {
                pokemonTeam.postValue(myTeam)
            }
        }
    }

    fun getListAfterAddingPokemon(pokemon: PokemonDataModel) {
        viewModelScope.launch {
            val response = addPokemonToTeam.addPokemon(pokemon)
            val myTeamAfterAdding = response?.teamList ?: ArrayList()
            val myMessageAfterAdding = response?.message ?: ""
            pokemonTeam.postValue(myTeamAfterAdding)
            message.postValue(myMessageAfterAdding)
        }
    }

    fun getListAfterDeletingPokemon(pokemon: PokemonEntity) {
        viewModelScope.launch {
            val myTeamAfterDeleting = DeletePokemonFromTeam(deletedPokemonFromTeamRepository).getTeamAfterDeleting(pokemon) ?: ArrayList()
            pokemonTeam.postValue(myTeamAfterDeleting)
        }
    }
}
