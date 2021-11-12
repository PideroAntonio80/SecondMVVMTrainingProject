package com.example.secondmvvmtrainingproject.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.secondmvvmtrainingproject.R
import com.example.secondmvvmtrainingproject.databinding.PokemonRowBinding
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonDataModel

class PokemonAdapter(private val pokemons: List<PokemonDataModel>,
                     private val context: Context,
                     private val itemClickListener: ItemClickListener?
                     ) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pokemon_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int = pokemons.size


    inner class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        private val binding = PokemonRowBinding.bind(view)

        fun bind(pokemon: PokemonDataModel) {
            binding.tvName.text = pokemon.name
            Glide.with(binding.ivPicture.context).load(pokemon.img).into(binding.ivPicture)

            itemView.setOnClickListener {
                itemClickListener?.onItemClick(pokemon)
            }
        }
    }

    interface ItemClickListener {
        fun onItemClick(item: PokemonDataModel)
    }
}