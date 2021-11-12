package com.example.secondmvvmtrainingproject.presentation.pokemonteam.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.secondmvvmtrainingproject.data.local.PokemonApplication
import com.example.secondmvvmtrainingproject.R
import com.example.secondmvvmtrainingproject.data.local.model.PokemonEntity
import com.example.secondmvvmtrainingproject.databinding.ActivityPokemonTeamBinding
import com.example.secondmvvmtrainingproject.presentation.pokemonteam.adapter.PokemonTeamAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PokemonTeamActivity : AppCompatActivity(), PokemonTeamAdapter.ItemTeamClickListener {

    private lateinit var binding: ActivityPokemonTeamBinding

    private lateinit var myAdapter: PokemonTeamAdapter
    private lateinit var myLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        myAdapter = PokemonTeamAdapter(mutableListOf(), this, this)
        myLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        getPokemons()

        binding.rvRecyclerTeam.apply {
            setHasFixedSize(true)
            layoutManager = myLayoutManager
            adapter = myAdapter
        }
        /* Lo anterior es como poner esto:
        binding.rvRecyclerTeam.setHasFixedSize(true)
        binding.rvRecyclerTeam.layoutManager = layoutManager
        binding.rvRecyclerTeam.adapter = myAdapter */
    }

    private fun getPokemons() {
        doAsync {
            val pokemons = PokemonApplication.database.pokemonDao().getPokemonTeam()
            uiThread {
                myAdapter.setPokemons(pokemons)
            }
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

    override fun onDeletePokemon(item: PokemonEntity) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.dialog_delete_title)
            .setPositiveButton(R.string.dialog_delete_confirm, { dialogInterface, i ->
                doAsync {
                    PokemonApplication.database.pokemonDao().deletePokemonFromTeam(item)
                    uiThread {
                        myAdapter.deletePokemon(item)
                    }
                }
            })
            .setNegativeButton(R.string.dialog_delete_cancel, null)
            .show()
        
        if (item.name == binding.tvNameTeamDetail.text) {
            binding.teamDetail.visibility = View.GONE
        }
    }

    override fun onPause() {
        binding.teamDetail.visibility = View.GONE
        super.onPause()
    }
}
