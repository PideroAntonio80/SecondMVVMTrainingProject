package com.example.secondmvvmtrainingproject.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PokemonEntity")
class PokemonEntity(@PrimaryKey(autoGenerate = false) val id: Long,
                    val name: String,
                    val img: String,
                    val weight: String,
                    val height: String,
                    val type: String) {
}