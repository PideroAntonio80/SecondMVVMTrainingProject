package com.example.secondmvvmtrainingproject.presentation.pokemonteam.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.secondmvvmtrainingproject.R
import com.example.secondmvvmtrainingproject.data.local.PokemonApplication
import com.example.secondmvvmtrainingproject.databinding.ActivityPokemonTeamBinding
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity
import com.example.secondmvvmtrainingproject.presentation.pokemonteam.view.adapter.PokemonTeamAdapter
import com.example.secondmvvmtrainingproject.presentation.pokemonteam.viewmodel.PokemonTeamViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PokemonTeamActivity : AppCompatActivity(), PokemonTeamAdapter.ItemTeamClickListener {

    private lateinit var binding: ActivityPokemonTeamBinding

    private lateinit var myAdapter: PokemonTeamAdapter
    private lateinit var myLayoutManager: LinearLayoutManager

    private val pokemonTeamViewModel: PokemonTeamViewModel by viewModels()

    private var teamList: ArrayList<PokemonEntity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pokemonTeamViewModel.onCreate()

        pokemonTeamViewModel.pokemonTeam.observe(this, {currentList ->
            teamList = currentList

            setupRecyclerView()
        })
    }

    private fun setupRecyclerView() {
        myAdapter = teamList?.let { PokemonTeamAdapter(it, this, this) }!!
        myLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.rvRecyclerTeam.apply {
            setHasFixedSize(true)
            layoutManager = myLayoutManager
            adapter = myAdapter

            /* Lo anterior es como poner esto:
            binding.rvRecyclerTeam.setHasFixedSize(true)
            binding.rvRecyclerTeam.layoutManager = layoutManager
            binding.rvRecyclerTeam.adapter = myAdapter */
        }
    }

    override fun onItemClick(item: PokemonEntity) {
        with(binding) {
            teamDetail.visibility = View.VISIBLE
            Glide.with(ivPictureTeamDetail.context).load(item.img).circleCrop().into(ivPictureTeamDetail)
            tvNameTeamDetail.text = item.name
            tvWeightDetailFill.text = item.weight
            tvHeightDetailFill.text = item.height
            tvTypeDetailFill.text = item.type
        }
    }

    override fun onDeletePokemon(pokemon: PokemonEntity) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.dialog_delete_title)
            .setPositiveButton(R.string.dialog_delete_confirm, { dialogInterface, i ->
                pokemonTeamViewModel.getListAfterDeletingPokemon(pokemon)

                pokemonTeamViewModel.pokemonTeam.observe(this, {currentList ->
                    teamList = currentList

                    setupRecyclerView()
                })
            })
            .setNegativeButton(R.string.dialog_delete_cancel, null)
            .show()
        
        if (pokemon.name == binding.tvNameTeamDetail.text) {
            binding.teamDetail.visibility = View.GONE
        }
    }

    override fun onPause() {
        binding.teamDetail.visibility = View.GONE
        super.onPause()
    }
}
