package com.example.secondmvvmtrainingproject.domain

import com.example.secondmvvmtrainingproject.repository.PokemonRepository

class GetAllPokemonsFeature {

    private val repository = PokemonRepository()

    /* Con esto de debajo consigo que para llamar a este método no tengo que poner la instancia de
    la clase + punto + el método. Así simplemente con instanciar la clase, ya llamo directamente al
    metodo con operator invoke*/
    suspend operator fun invoke() = repository.getAllPokemon()
}