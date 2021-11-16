package com.example.secondmvvmtrainingproject.presentation.pokemonbooklist.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.secondmvvmtrainingproject.R
import com.example.secondmvvmtrainingproject.databinding.FragmentDetailBinding
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonDataModel
import com.example.secondmvvmtrainingproject.presentation.pokemonteam.view.PokemonTeamActivity
import com.example.secondmvvmtrainingproject.presentation.pokemonteam.viewmodel.PokemonTeamViewModel
import com.google.android.material.snackbar.Snackbar

private const val EXTRA_POKEMON = "param1"

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private lateinit var pokemonDetail: PokemonDataModel

    private val pokemonTeamViewModel: PokemonTeamViewModel by viewModels()

    private var myMessage: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pokemonDetail = it.getParcelable(EXTRA_POKEMON)!!
        }
        pokemonTeamViewModel.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailBinding.inflate(inflater, container, false)

        initView(pokemonDetail)

        return binding.root
    }

    private fun initView(pokemon: PokemonDataModel) {
        with(binding) {
            Glide.with(binding.ivPictureDetail.context).load(pokemon.img).into(ivPictureDetail)
            tvNameDetail.text = pokemon.name
            tvWeightDetailFill.text = pokemon.weight
            tvHeightDetailFill.text = pokemon.height
            tvTypeDetailFill.text = pokemon.type?.get(0)
        }

        binding.fabAddFav.setOnClickListener {

            pokemonTeamViewModel.getListAfterAddingPokemon(pokemon)

            pokemonTeamViewModel.message.observe(this.viewLifecycleOwner, { currentMessage ->
                myMessage = currentMessage

                makeSnack(myMessage)
            })
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