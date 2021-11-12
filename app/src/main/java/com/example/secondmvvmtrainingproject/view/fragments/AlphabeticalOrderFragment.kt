package com.example.secondmvvmtrainingproject.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondmvvmtrainingproject.R
import com.example.secondmvvmtrainingproject.databinding.FragmentAlphabeticalOrderBinding
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonDataModel
import com.example.secondmvvmtrainingproject.view.adapters.PokemonAdapter

private const val EXTRA_ALPHABETICAL_LIST = "param1"

class AlphabeticalOrderFragment : Fragment(), PokemonAdapter.ItemClickListener {

    private lateinit var binding: FragmentAlphabeticalOrderBinding

    private lateinit var pokemonAlphabeticalList: ArrayList<PokemonDataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pokemonAlphabeticalList = it.getParcelableArrayList<PokemonDataModel>(EXTRA_ALPHABETICAL_LIST) as ArrayList<PokemonDataModel>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAlphabeticalOrderBinding.inflate(inflater, container, false)

        initView(getAlphabeticalOrderingList(pokemonAlphabeticalList))

        return binding.root
    }

    private fun initView(myPokemons: ArrayList<PokemonDataModel>) {
        val layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.rvRecyclerAphabeticalList.layoutManager = layoutManager
        binding.rvRecyclerAphabeticalList.adapter = PokemonAdapter(myPokemons, binding.root.context, this)
    }

    private fun getAlphabeticalOrderingList(myOrderedPokemons: ArrayList<PokemonDataModel>): ArrayList<PokemonDataModel> {
        val sortedList = myOrderedPokemons.sortedBy { it.name }.toList()
        return sortedList as ArrayList<PokemonDataModel>
    }

    companion object {
        @JvmStatic
        fun newInstance(pokemonsAlphabeticalList: ArrayList<PokemonDataModel>) =
            AlphabeticalOrderFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(EXTRA_ALPHABETICAL_LIST, pokemonsAlphabeticalList)
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