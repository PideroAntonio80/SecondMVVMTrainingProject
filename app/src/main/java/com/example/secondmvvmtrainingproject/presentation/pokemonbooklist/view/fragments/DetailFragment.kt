package com.example.secondmvvmtrainingproject.presentation.pokemonbooklist.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.secondmvvmtrainingproject.dao.PokemonApplication
import com.example.secondmvvmtrainingproject.R
import com.example.secondmvvmtrainingproject.dao.PokemonEntity
import com.example.secondmvvmtrainingproject.databinding.FragmentDetailBinding
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonDataModel
import com.example.secondmvvmtrainingproject.presentation.pokemonteam.view.PokemonTeamActivity
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

private const val EXTRA_POKEMON = "param1"

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private lateinit var pokemonDetail: PokemonDataModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pokemonDetail = it.getParcelable(EXTRA_POKEMON)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailBinding.inflate(inflater, container, false)

        initView(pokemonDetail)

        binding.fabAddFav.setOnClickListener {
            val pokemonEntity = PokemonEntity(id = pokemonDetail.id,
                name = pokemonDetail.name, img = pokemonDetail.img,
                weight = pokemonDetail.weight,
                height = pokemonDetail.height,
                type = pokemonDetail.type?.get(0).toString())
            doAsync {
                if (PokemonApplication.database.pokemonDao().getPokemonTeam().size >= 3) {
                    uiThread {
                        makeSnack(getString(R.string.full_team))
                    }
                } else {
                    PokemonApplication.database.pokemonDao().addPokemonToTeam(pokemonEntity)

                    uiThread {
                        makeSnack(getString(R.string.fab_pokemon_added))
                    }
                }
            }
        }
        return binding.root
    }

    private fun initView(pokemon: PokemonDataModel) {
        with(binding) {
            Glide.with(binding.ivPictureDetail.context).load(pokemon.img).into(ivPictureDetail)
            tvNameDetail.text = pokemon.name
            tvWeightDetailFill.text = pokemon.weight
            tvHeightDetailFill.text = pokemon.height
            tvTypeDetailFill.text = pokemon.type?.get(0) // TODO Undo
        }
    }

    private fun makeSnack(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
            .setAnchorView(binding.fabAddFav)
            .setAction(R.string.pokemon_added_to_go) {
                startActivity(Intent(context, PokemonTeamActivity::class.java))
            }
            .show()
    }

    companion object {
        @JvmStatic
        fun newInstance(myPokemon: PokemonDataModel) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_POKEMON, myPokemon)
                }
            }
    }
}