package com.example.secondmvvmtrainingproject.presentation.pokemongame.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.secondmvvmtrainingproject.databinding.ActivityPokemonGameBinding

class PokemonGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonGameBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}