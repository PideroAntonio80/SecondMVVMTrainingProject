package com.example.secondmvvmtrainingproject.presentation.pokemonteam.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.secondmvvmtrainingproject.R
import com.example.secondmvvmtrainingproject.databinding.PokemonRowTeamBinding
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntity

class PokemonTeamAdapter(private var pokemons: ArrayList<PokemonEntity>,
                         private val context: Context,
                         private val itemTeamClickListener: ItemTeamClickListener?
) : RecyclerView.Adapter<PokemonTeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pokemon_row_team, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int = pokemons.size

    /*fun setPokemons(pokemons: MutableList<PokemonEntity>) {
        this.pokemons = pokemons
        notifyDataSetChanged()
    }

    fun addPokemon(pokemon: PokemonEntity) {
        if (!pokemons.contains(pokemon)) {
            pokemons.add(pokemon)
            notifyItemInserted(pokemons.size - 1)
        } else {
            Toast.makeText(context, R.string.repeated_pokemon, Toast.LENGTH_SHORT).show()
        }
    }

    fun deletePokemon(pokemon: PokemonEntity) {
        val index = pokemons.indexOf(pokemon)
        if (index != -1) {
            pokemons.removeAt(index)
            notifyItemRemoved(index)
        }
    }*/

    inner class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        private val binding = PokemonRowTeamBinding.bind(view)

        fun bind(pokemon: PokemonEntity) {
            binding.tvName.text = pokemon.name
            Glide.with(binding.ivPicture.context).load(pokemon.img).circleCrop().into(binding.ivPicture)

            itemView.setOnClickListener {
                itemTeamClickListener?.onItemClick(pokemon)
            }

            binding.ibDeletePokemon.setOnClickListener {
                itemTeamClickListener?.onDeletePokemon(pokemon)
            }
        }
    }

    interface ItemTeamClickListener {
        fun onItemClick(item: PokemonEntity)
        fun onDeletePokemon(item: PokemonEntity)
    }
}