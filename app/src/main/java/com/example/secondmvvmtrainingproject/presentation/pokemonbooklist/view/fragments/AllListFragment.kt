package com.example.secondmvvmtrainingproject.presentation.pokemonbooklist.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondmvvmtrainingproject.R
import com.example.secondmvvmtrainingproject.databinding.FragmentAllListBinding
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonDataModel
import com.example.secondmvvmtrainingproject.presentation.pokemonbooklist.view.adapter.PokemonAdapter

private const val EXTRA_LIST = "param1"

class AllListFragment : Fragment(), PokemonAdapter.ItemClickListener {

    private lateinit var binding: FragmentAllListBinding

    private lateinit var pokemonlist: ArrayList<PokemonDataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pokemonlist = it.getParcelableArrayList<PokemonDataModel>(EXTRA_LIST) as ArrayList<PokemonDataModel>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAllListBinding.inflate(inflater, container, false)

        initView(pokemonlist)

        return binding.root
    }

    private fun initView(myPokemons: ArrayList<PokemonDataModel>) {
        val layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.rvRecyclerAllList.layoutManager = layoutManager
        binding.rvRecyclerAllList.adapter = PokemonAdapter(myPokemons, binding.root.context, this)
    }

    companion object {
        @JvmStatic
        fun newInstance(pokemonsList: ArrayList<PokemonDataModel>) =
            AllListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(EXTRA_LIST, pokemonsList)
                }
            }
    }

    override fun onItemClick(item: PokemonDataModel) {
        changeFragment(DetailFragment.newInstance(item))
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.flContainer, fragment).commit()
    }
}


