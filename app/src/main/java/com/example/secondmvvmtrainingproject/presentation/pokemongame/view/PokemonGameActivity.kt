package com.example.secondmvvmtrainingproject.presentation.pokemongame.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.secondmvvmtrainingproject.R
import com.example.secondmvvmtrainingproject.databinding.ActivityPokemonGameBinding
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity
import com.example.secondmvvmtrainingproject.presentation.pokemongame.viewmodel.PokemonGameViewModel
import com.example.secondmvvmtrainingproject.presentation.pokemonteam.view.PokemonTeamActivity
import com.google.android.material.snackbar.Snackbar

class PokemonGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonGameBinding

    private val pokemonGameViewModel: PokemonGameViewModel by viewModels()

    private var gameTeam: ArrayList<PokemonEntity>? = null
    private var gameEnemyTeam: ArrayList<PokemonEntity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.mbSecretRoom.isEnabled = false
        binding.mbFight.isEnabled = false

        pokemonGameViewModel.onCreate()

        pokemonGameViewModel.setEnemyTeam()
        pokemonGameViewModel.pokemonGameEnemyTeam.observe(this, { currentEnemyTeamList ->
            gameEnemyTeam = currentEnemyTeamList
        })

        pokemonGameViewModel.pokemonGameMyTeam.observe(this, { currentMyTeamList ->
            gameTeam = currentMyTeamList

            if (gameTeam?.size!! < 3) {
                makeSnack(getString(R.string.game_team_min_players))
            } else {
                setMyTeamGame()
                setListeners()
            }
        })
    }

    private fun setMyTeamGame() {
        with(binding) {
            Glide.with(ibMyPok1).load(gameTeam?.get(0)?.img).circleCrop().centerCrop().into(ibMyPok1)
            Glide.with(ibMyPok2).load(gameTeam?.get(1)?.img).circleCrop().centerCrop().into(ibMyPok2)
            Glide.with(ibMyPok3).load(gameTeam?.get(2)?.img).circleCrop().centerCrop().into(ibMyPok3)
        }

        binding.mbFight.isEnabled = true
    }

    private fun setEnemyTeamGame() {
        println(gameEnemyTeam?.get(0)?.img)
        println(gameEnemyTeam?.get(1)?.img)
        println(gameEnemyTeam?.get(2)?.img)
        with(binding) {
            Glide.with(ibMyPok4).load(gameEnemyTeam?.get(0)?.img).circleCrop().centerCrop().into(ibMyPok4)
            Glide.with(ibMyPok5).load(gameEnemyTeam?.get(1)?.img).circleCrop().centerCrop().into(ibMyPok5)
            Glide.with(ibMyPok6).load(gameEnemyTeam?.get(2)?.img).circleCrop().centerCrop().into(ibMyPok6)
        }
    }

    private fun setListeners() {
        binding.mbFight.setOnClickListener {
            setEnemyTeamGame()
        }
        binding.mbRestart.setOnClickListener {
            finish()
            reload()
        }
        binding.mbSecretRoom.setOnClickListener {
            // TODO Cambiar esto
            binding.mbSecretRoom.setBackgroundColor(resources.getColor(R.color.green))
            binding.mbSecretRoom.setIconResource(R.drawable.ic_lock_open)
        }
    }

    private fun makeSnack(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
            .setAnchorView(binding.mbFight)
            .setAction(R.string.pokemon_added_to_go) {
                startActivity(Intent(this, PokemonTeamActivity::class.java))
            }
            .show()
    }

    private fun reload() {
        startActivity(Intent(this, PokemonGameActivity::class.java))
    }
}