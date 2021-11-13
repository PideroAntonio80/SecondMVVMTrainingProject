package com.example.secondmvvmtrainingproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity

@Database(entities = arrayOf(PokemonEntity::class), version = 3)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}