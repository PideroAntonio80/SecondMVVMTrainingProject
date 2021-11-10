package com.example.secondmvvmtrainingproject.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(PokemonEntity::class), version = 3)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}