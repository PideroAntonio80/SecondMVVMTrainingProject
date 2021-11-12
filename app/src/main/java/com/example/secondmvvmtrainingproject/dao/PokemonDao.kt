package com.example.secondmvvmtrainingproject.dao

import androidx.room.*

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