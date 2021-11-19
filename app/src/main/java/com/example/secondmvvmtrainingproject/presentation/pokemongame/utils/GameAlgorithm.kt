package com.example.secondmvvmtrainingproject.presentation.pokemongame.utils

import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntityGame

fun gameAlgorithm(good: PokemonEntityGame, enemy: PokemonEntityGame) {

    when(good.type) {
        "Ice" -> {
            when (enemy.type) {
                "Dragon", "Psychic", "Fighting", "Bug", "Grass", "Normal", "Water" -> {
                    good.victory = true
                    enemy.victory = false
                }
                "Ghost", "Ground", "Rock", "Electric", "Fire", "Poison" -> {
                    good.victory = false
                    enemy.victory = true
                }
                "Ice" -> {
                    if (good.weight > enemy.weight) {
                        good.victory = true
                        enemy.victory = false
                    } else {
                        good.victory = false
                        enemy.victory = true
                    }
                }
            }
        }
        "Dragon" -> {
            when (enemy.type) {
                "Ghost", "Fighting", "Electric", "Fire", "Bug", "Poison", "Normal" -> {
                    good.victory = true
                    enemy.victory = false
                }
                "Ice", "Psychic", "Ground", "Rock", "Grass", "Water" -> {
                    good.victory = false
                    enemy.victory = true
                }
                "Dragon" -> {
                    if (good.weight > enemy.weight) {
                        good.victory = true
                        enemy.victory = false
                    } else {
                        good.victory = false
                        enemy.victory = true
                    }
                }
            }
        }
        "Ghost" -> {
            when (enemy.type) {
                "Ice", "Fighting", "Psychic", "Bug", "Poison", "Normal", "Water" -> {
                    good.victory = true
                    enemy.victory = false
                }
                "Dragon", "Ground", "Rock", "Electric", "Fire", "Grass" -> {
                    good.victory = false
                    enemy.victory = true
                }
                "Ghost" -> {
                    if (good.weight > enemy.weight) {
                        good.victory = true
                        enemy.victory = false
                    } else {
                        good.victory = false
                        enemy.victory = true
                    }
                }
            }
        }
        "Fighting" -> {
            when (enemy.type) {
                "Ground", "Rock", "Bug", "Grass", "Normal", "Water" -> {
                    good.victory = true
                    enemy.victory = false
                }
                "Ice", "Dragon", "Ghost", "Psychic", "Electric", "Fire", "Poison" -> {
                    good.victory = false
                    enemy.victory = true
                }
                "Fighting" -> {
                    if (good.weight > enemy.weight) {
                        good.victory = true
                        enemy.victory = false
                    } else {
                        good.victory = false
                        enemy.victory = true
                    }
                }
            }
        }
        "Psychic" -> {
            when (enemy.type) {
                "Dragon", "Fighting", "Fire", "Bug", "Grass", "Poison", "Normal" -> {
                    good.victory = true
                    enemy.victory = false
                }
                "Ice", "Ghost", "Ground", "Rock", "Electric", "Water" -> {
                    good.victory = false
                    enemy.victory = true
                }
                "Psychic" -> {
                    if (good.weight > enemy.weight) {
                        good.victory = true
                        enemy.victory = false
                    } else {
                        good.victory = false
                        enemy.victory = true
                    }
                }
            }
        }
        "Ground" -> {
            when (enemy.type) {
                "Ice", "Dragon", "Ghost", "Psychic", "Rock", "Electric", "Poison" -> {
                    good.victory = true
                    enemy.victory = false
                }
                "Fighting", "Fire", "Bug", "Grass", "Normal", "Water" -> {
                    good.victory = false
                    enemy.victory = true
                }
                "Ground" -> {
                    if (good.weight > enemy.weight) {
                        good.victory = true
                        enemy.victory = false
                    } else {
                        good.victory = false
                        enemy.victory = true
                    }
                }
            }
        }
        "Rock" -> {
            when (enemy.type) {
                "Ice", "Dragon", "Ghost", "Psychic", "Fire", "Electric", "Poison" -> {
                    good.victory = true
                    enemy.victory = false
                }
                "Fighting", "Ground", "Bug", "Grass", "Normal", "Water" -> {
                    good.victory = false
                    enemy.victory = true
                }
                "Rock" -> {
                    if (good.weight > enemy.weight) {
                        good.victory = true
                        enemy.victory = false
                    } else {
                        good.victory = false
                        enemy.victory = true
                    }
                }
            }
        }
        "Electric" -> {
            when (enemy.type) {
                "Ice", "Ghost", "Fighting", "Psychic", "Fire", "Normal", "Water" -> {
                    good.victory = true
                    enemy.victory = false
                }
                "Dragon", "Ground", "Rock", "Bug", "Grass", "Poison" -> {
                    good.victory = false
                    enemy.victory = true
                }
                "Electric" -> {
                    if (good.weight > enemy.weight) {
                        good.victory = true
                        enemy.victory = false
                    } else {
                        good.victory = false
                        enemy.victory = true
                    }
                }
            }
        }
        "Fire" -> {
            when (enemy.type) {
                "Ice", "Ghost", "Fighting", "Ground", "Bug", "Grass", "Normal" -> {
                    good.victory = true
                    enemy.victory = false
                }
                "Dragon", "Psychic", "Rock", "Electric", "Poison", "Water" -> {
                    good.victory = false
                    enemy.victory = true
                }
                "Fire" -> {
                    if (good.weight > enemy.weight) {
                        good.victory = true
                        enemy.victory = false
                    } else {
                        good.victory = false
                        enemy.victory = true
                    }
                }
            }
        }
        "Bug" -> {
            when (enemy.type) {
                "Ground", "Rock", "Electric", "Grass", "Poison" -> {
                    good.victory = true
                    enemy.victory = false
                }
                "Ice", "Dragon", "Ghost", "Fighting",  "Psychic", "Fire", "Normal", "Water" -> {
                    good.victory = false
                    enemy.victory = true
                }
                "Bug" -> {
                    if (good.weight > enemy.weight) {
                        good.victory = true
                        enemy.victory = false
                    } else {
                        good.victory = false
                        enemy.victory = true
                    }
                }
            }
        }
        "Grass" -> {
            when (enemy.type) {
                "Dragon", "Ghost", "Ground", "Rock", "Electric", "Water" -> {
                    good.victory = true
                    enemy.victory = false
                }
                "Ice", "Fighting", "Psychic", "Fire", "Bug", "Poison", "Normal" -> {
                    good.victory = false
                    enemy.victory = true
                }
                "Grass" -> {
                    if (good.weight > enemy.weight) {
                        good.victory = true
                        enemy.victory = false
                    } else {
                        good.victory = false
                        enemy.victory = true
                    }
                }
            }
        }
        "Poison" -> {
            when (enemy.type) {
                "Ice", "Fighting", "Electric", "Fire", "grass", "Normal", -> {
                    good.victory = true
                    enemy.victory = false
                }
                "Dragon", "Ghost", "Psychic", "Ground", "Rock", "Bug", "Water" -> {
                    good.victory = false
                    enemy.victory = true
                }
                "Poison" -> {
                    if (good.weight > enemy.weight) {
                        good.victory = true
                        enemy.victory = false
                    } else {
                        good.victory = false
                        enemy.victory = true
                    }
                }
            }
        }
        "Normal" -> {
            when (enemy.type) {
                "Ground", "Rock", "Bug", "Water", "grass" -> {
                    good.victory = true
                    enemy.victory = false
                }
                "Ice", "Dragon", "Ghost", "Fighting", "Psychic", "Electric", "Fire", "Poison" -> {
                    good.victory = false
                    enemy.victory = true
                }
                "Normal" -> {
                    if (good.weight > enemy.weight) {
                        good.victory = true
                        enemy.victory = false
                    } else {
                        good.victory = false
                        enemy.victory = true
                    }
                }
            }
        }
        "Water" -> {
            when (enemy.type) {
                "Dragon", "Psychic", "Ground", "Rock", "Fire", "Bug", "Poison" -> {
                    good.victory = true
                    enemy.victory = false
                }
                "Ice", "Ghost", "Fighting", "Electric", "grass", "Normal" -> {
                    good.victory = false
                    enemy.victory = true
                }
                "Water" -> {
                    if (good.weight > enemy.weight) {
                        good.victory = true
                        enemy.victory = false
                    } else {
                        good.victory = false
                        enemy.victory = true
                    }
                }
            }
        }
    }
}