package com.example.secondmvvmtrainingproject.data.network.response.pokemons.getAllPokemons

import com.google.gson.annotations.SerializedName

data class GetAllPokemonsServiceResponse(

	@SerializedName("pokemon")
	val pokemonList: List<PokemonItem>? = null
)

data class PrevEvolutionItem(

	@SerializedName("num")
	val num: String? = null,

	@SerializedName("name")
	val name: String? = null
)

data class NextEvolutionItem(

	@SerializedName("num")
	val num: String? = null,

	@SerializedName("name")
	val name: String? = null
)

data class PokemonItem(

	@SerializedName("img")
	val img: String? = null,

	@SerializedName("egg")
	val egg: String? = null,

	@SerializedName("candy")
	val candy: String? = null,

	@SerializedName("num")
	val num: String? = null,

	@SerializedName("weight")
	val weight: String? = null,

	@SerializedName("type")
	val type: List<String>? = null,

	@SerializedName("weaknesses")
	val weaknesses: List<String>? = null,

	@SerializedName("name")
	val name: String? = null,

//	@SerializedName("avg_spawns")
//	val avgSpawns: Int? = null,

	@SerializedName("multipliers")
	val multipliers: List<Double>? = null,

	@SerializedName("id")
	val id: Int? = null,

	@SerializedName("spawn_time")
	val spawnTime: String? = null,

	@SerializedName("height")
	val height: String? = null,

	@SerializedName("spawn_chance")
	val spawnChance: Double? = null,

	@SerializedName("prev_evolution")
	val prevEvolution: List<PrevEvolutionItem>? = null,

	@SerializedName("candy_count")
	val candyCount: Int? = null,

	@SerializedName("next_evolution")
	val nextEvolution: List<NextEvolutionItem>? = null
)
