package com.example.secondmvvmtrainingproject.data.local

import androidx.room.*
import com.example.secondmvvmtrainingproject.data.local.model.PokemonEntity

@Dao
interface PokemonDao {
    @Query("SELECT * FROM PokemonEntity")
    fun getPokemonTeam() : MutableList<PokemonEntity>

    @Insert
    fun addPokemonToTeam(pokemonEntity: PokemonEntity)

    @Update
    fun updatePokemonTeam(pokemonEntity: PokemonEntity)

    @Delete
    fun deletePokemonFromTeam(pokemonEntity: PokemonEntity)
}