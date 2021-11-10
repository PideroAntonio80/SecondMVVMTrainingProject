package com.example.secondmvvmtrainingproject.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDataModel(val id: Long,
                            val name: String,
                            val img: String,
                            val weight: String,
                            val height: String,
                            val type: Array<String>?) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PokemonDataModel

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
