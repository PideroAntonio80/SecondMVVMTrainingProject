package com.example.secondmvvmtrainingproject.view.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.secondmvvmtrainingproject.PokemonApplication
import com.example.secondmvvmtrainingproject.R
import com.example.secondmvvmtrainingproject.dao.PokemonEntity
import com.example.secondmvvmtrainingproject.databinding.FragmentSearchingBinding
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonDataModel
import com.example.secondmvvmtrainingproject.view.PokemonTeamActivity
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

private const val EXTRA_SEARCH_LIST = "param1"

class SearchingFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentSearchingBinding

    private lateinit var pokemonSearchlist: ArrayList<PokemonDataModel>
    private var pokemon: PokemonDataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pokemonSearchlist = it.getParcelableArrayList<PokemonDataModel>(EXTRA_SEARCH_LIST) as ArrayList<PokemonDataModel>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchingBinding.inflate(inflater, container, false)

        binding.svSearch.setOnQueryTextListener(this)

        return binding.root
    }

    private fun initView(myPokemonSearched: PokemonDataModel?) {
        binding.svSearch.setOnQueryTextListener(this)
        with(binding) {
            if (myPokemonSearched != null) {
                Glide.with(binding.ivPictureSearched.context).load(myPokemonSearched.img).into(ivPictureSearched)
                tvNameSearched.text = myPokemonSearched.name
                tvWeightSearchedFill.text = myPokemonSearched.weight
                tvHeightSearchedFill.text = myPokemonSearched.height
                tvTypeSearchedFill.text = myPokemonSearched.type?.get(0)
            }

            fabAddFav.setOnClickListener {
                val pokemonEntity = myPokemonSearched?.let { pokemon ->
                    PokemonEntity(
                        id = pokemon.id,
                        name = pokemon.name,
                        img = pokemon.img,
                        weight = pokemon.weight,
                        height = pokemon.height,
                        type = pokemon.type?.get(0).toString()
                    )
                }
                doAsync {
                    if (PokemonApplication.database.pokemonDao().getPokemonTeam().size >= 3) {
                        uiThread {
                            makeSnack(getString(R.string.full_team))
                        }
                    } else {
                        PokemonApplication.database.pokemonDao().addPokemonToTeam(pokemonEntity!!)

                        uiThread {
                            makeSnack(getString(R.string.fab_pokemon_added))
                        }
                    }
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(myPokemonSearchList: ArrayList<PokemonDataModel>) =
            SearchingFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(EXTRA_SEARCH_LIST, myPokemonSearchList)
                }
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

    private fun searchByName(query: String): List<PokemonDataModel> {
        return pokemonSearchlist.filter { it.name == query }.toList()
    }

        override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            pokemon = searchByName(query.lowercase().replaceFirstChar { l -> l.uppercase() })[0]
            binding.rlBackgroundFileSearched.visibility = View.GONE
            binding.rlPokemonFileSearched.visibility = View.VISIBLE
            initView(pokemon)
            hideKeyboard()
        }
            return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun Fragment.hideKeyboard() {
        activity?.hideKeyboard(binding.root)
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

