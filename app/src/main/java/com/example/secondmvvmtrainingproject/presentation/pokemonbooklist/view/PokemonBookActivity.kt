package com.example.secondmvvmtrainingproject.presentation.pokemonbooklist.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.secondmvvmtrainingproject.R
import com.example.secondmvvmtrainingproject.databinding.ActivityPokemonBookBinding
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonDataModel
import com.example.secondmvvmtrainingproject.presentation.pokemonbooklist.view.fragments.AllListFragment
import com.example.secondmvvmtrainingproject.presentation.pokemonbooklist.view.fragments.AlphabeticalOrderFragment
import com.example.secondmvvmtrainingproject.presentation.pokemonbooklist.view.fragments.SearchingFragment
import com.example.secondmvvmtrainingproject.presentation.pokemonbooklist.viewmodel.PokemonViewModel

class PokemonBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonBookBinding

    private val pokemonViewModel: PokemonViewModel by viewModels()

    private lateinit var list: MutableList<PokemonDataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pokemonViewModel.onCreate()

        list = ArrayList()

        pokemonViewModel.pokemonDataModel.observe(this, { currentPokemon ->
            list = currentPokemon as MutableList<PokemonDataModel>

            supportFragmentManager.beginTransaction().add(R.id.flContainer, AllListFragment.newInstance(
                list as ArrayList<PokemonDataModel>
            )).commit()
        })

        binding.bnbNavigator.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.allListFragment -> showFragment(AllListFragment.newInstance(list as ArrayList<PokemonDataModel>))
                R.id.orderedListFragment -> showFragment(AlphabeticalOrderFragment.newInstance(list as ArrayList<PokemonDataModel>))
                R.id.searchFragment -> showFragment(SearchingFragment.newInstance(list as ArrayList<PokemonDataModel>))
            }

            true
        }
    }

     private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit()
    }
}
