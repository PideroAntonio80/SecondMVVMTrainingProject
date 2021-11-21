package com.example.secondmvvmtrainingproject.presentation.pokemongame.view

import android.animation.Animator
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.secondmvvmtrainingproject.R
import com.example.secondmvvmtrainingproject.databinding.ActivityPokemonGameBinding
import com.example.secondmvvmtrainingproject.domain.model.pokemons.PokemonEntityGame
import com.example.secondmvvmtrainingproject.presentation.pokemongame.utils.gameAlgorithm
import com.example.secondmvvmtrainingproject.presentation.pokemongame.view.fragments.OptionsFragment
import com.example.secondmvvmtrainingproject.presentation.pokemongame.viewmodel.PokemonGameViewModel
import com.example.secondmvvmtrainingproject.presentation.pokemonteam.view.PokemonTeamActivity
import com.google.android.material.snackbar.Snackbar

class PokemonGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonGameBinding

    private val pokemonGameViewModel: PokemonGameViewModel by viewModels()

    private var gameTeam: ArrayList<PokemonEntityGame>? = null
    private var gameEnemyTeam: ArrayList<PokemonEntityGame>? = null

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mbSecretRoom.isEnabled = false
        binding.mbFight.isEnabled = false

        pokemonGameViewModel.onCreate()

        pokemonGameViewModel.setEnemyTeam()
        pokemonGameViewModel.pokemonGameEnemyTeam.observe(this, { currentEnemyTeamList ->
            gameEnemyTeam = currentEnemyTeamList
        })

        pokemonGameViewModel.pokemonGameMyTeam.observe(this, { currentMyTeamList ->
            gameTeam = currentMyTeamList

            if (gameTeam?.size!! != 3) {  // TODO Si es igual a cero no se hace el snack??????
                makeSnack(getString(R.string.game_team_min_players))
            } else {
                setMyTeamGame()
                setListeners()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        mediaPlayer = MediaPlayer.create(this, R.raw.rocky)
    }

    private fun setMyTeamGame() {
        with(binding) {
            Glide.with(ibMyPok1).load(gameTeam?.get(0)?.img).circleCrop().centerCrop().into(ibMyPok1)
            tvName1.text = gameTeam?.get(0)?.name.toString().trim()
            Glide.with(ibMyPok2).load(gameTeam?.get(1)?.img).circleCrop().centerCrop().into(ibMyPok2)
            tvName2.text = gameTeam?.get(1)?.name.toString().trim()
            Glide.with(ibMyPok3).load(gameTeam?.get(2)?.img).circleCrop().centerCrop().into(ibMyPok3)
            tvName3.text = gameTeam?.get(2)?.name.toString().trim()
        }

        binding.mbFight.isEnabled = true
    }

    private fun setEnemyTeamGame() {
        with(binding) {
            Glide.with(ibMyPok4).load(gameEnemyTeam?.get(0)?.img).circleCrop().centerCrop().into(ibMyPok4)
            tvName4.text = gameEnemyTeam?.get(0)?.name.toString().trim()
            Glide.with(ibMyPok5).load(gameEnemyTeam?.get(1)?.img).circleCrop().centerCrop().into(ibMyPok5)
            tvName5.text = gameEnemyTeam?.get(1)?.name.toString().trim()
            Glide.with(ibMyPok6).load(gameEnemyTeam?.get(2)?.img).circleCrop().centerCrop().into(ibMyPok6)
            tvName6.text = gameEnemyTeam?.get(2)?.name.toString().trim()
        }
    }

    private fun setListeners() {
        binding.mbFight.setOnClickListener {
            setEnemyTeamGame()
            battleStart()
            mediaPlayer?.start()
        }
        binding.mbRestart.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
            finish()
            reload()
        }
        binding.mbSecretRoom.setOnClickListener {
            binding.game.visibility = View.GONE
            val fragment = OptionsFragment()
            supportFragmentManager.beginTransaction().add(R.id.containerGame, fragment).addToBackStack(null).commit()
        }
    }

    private fun makeSnack(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
            .setAnchorView(binding.mbFight)
            .setAction(R.string.pokemon_added_to_go) {
                startActivity(Intent(this, PokemonTeamActivity::class.java))
            }
            .show()
    }

    private fun reload() {
        startActivity(Intent(this, PokemonGameActivity::class.java))
    }

    private fun battleStart() {
        var myVictories = 0

        val myPok1 = gameTeam?.get(0)
        val myPok2 = gameTeam?.get(1)
        val myPok3 = gameTeam?.get(2)
        val enemy1 = gameEnemyTeam?.get(0)
        val enemy2 = gameEnemyTeam?.get(1)
        val enemy3 = gameEnemyTeam?.get(2)

        gameAlgorithm(myPok1!!, enemy1!!)
        gameAlgorithm(myPok2!!, enemy2!!)
        gameAlgorithm(myPok3!!, enemy3!!)

        with(binding) {

            /***************************************/
            animationView.visibility = View.VISIBLE
            animationView.repeatCount = 2
            animationView.playAnimation()

            animationView.addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {}

                override fun onAnimationEnd(animation: Animator?) {
                    if (myPok1.victory) {
                        ivWin1.visibility = View.VISIBLE
                        myVictories++
                    } else {
                        ivLose1.visibility = View.VISIBLE
                    }

                    if (enemy1.victory) {
                        ivWin4.visibility = View.VISIBLE
                    } else {
                        ivLose4.visibility = View.VISIBLE
                    }

                    /***************************************/
                    animationView2.visibility = View.VISIBLE
                    animationView2.repeatCount = 2
                    animationView2.playAnimation()

                    animationView2.addAnimatorListener(object : Animator.AnimatorListener {
                        override fun onAnimationRepeat(animation: Animator?) {}

                        override fun onAnimationEnd(animation: Animator?) {
                            if (myPok2.victory) {
                                ivWin2.visibility = View.VISIBLE
                                myVictories++
                            } else {
                                ivLose2.visibility = View.VISIBLE
                            }

                            if (enemy2.victory) {
                                ivWin5.visibility = View.VISIBLE
                            } else {
                                ivLose5.visibility = View.VISIBLE
                            }

                            /***************************************/
                            animationView3.visibility = View.VISIBLE
                            animationView3.repeatCount = 2
                            animationView3.playAnimation()

                            animationView3.addAnimatorListener(object : Animator.AnimatorListener {
                                override fun onAnimationRepeat(animation: Animator?) {}

                                override fun onAnimationEnd(animation: Animator?) {
                                    if (myPok3.victory) {
                                        ivWin3.visibility = View.VISIBLE
                                        myVictories++
                                    } else {
                                        ivLose3.visibility = View.VISIBLE
                                    }

                                    if (enemy3.victory) {
                                        ivWin6.visibility = View.VISIBLE
                                    } else {
                                        ivLose6.visibility = View.VISIBLE
                                    }

                                    mediaPlayer?.stop()
                                    mediaPlayer?.release()
                                    mediaPlayer = null

                                    /***************************************/
                                    if (myVictories >=2) {
                                        tvResultWin.visibility = View.VISIBLE
                                        tvResultWin.animate().apply {
                                            mediaPlayer = MediaPlayer.create(this@PokemonGameActivity, R.raw.winbattle)
                                            mediaPlayer?.start()
                                            duration = 3000
                                            rotationYBy(360f)
                                        }.withEndAction {
                                            mediaPlayer?.stop()
                                            mediaPlayer?.release()
                                            mediaPlayer = null
                                        }

                                        mbSecretRoom.isEnabled = true
                                        mbSecretRoom.setBackgroundColor(resources.getColor(R.color.green))
                                        mbSecretRoom.setIconResource(R.drawable.ic_lock_open)
                                    } else {
                                        tvResultLose.visibility = View.VISIBLE
                                        tvResultLose.animate().apply {
                                            mediaPlayer = MediaPlayer.create(this@PokemonGameActivity, R.raw.loser)
                                            mediaPlayer?.start()
                                            duration = 3000
                                            rotationYBy(360f)
                                        }.withEndAction {
                                            mediaPlayer?.stop()
                                            mediaPlayer?.release()
                                            mediaPlayer = null
                                        }
                                    }
                                }

                                override fun onAnimationCancel(animation: Animator?) {
                                    animationView3.visibility = View.GONE
                                }

                                override fun onAnimationStart(animation: Animator?) {}
                            })
                        }

                        override fun onAnimationCancel(animation: Animator?) {
                            animationView2.visibility = View.GONE
                        }

                        override fun onAnimationStart(animation: Animator?) {}
                    })
                }

                override fun onAnimationCancel(animation: Animator?) {
                    animationView.visibility = View.GONE
                }

                override fun onAnimationStart(animation: Animator?) {
                    //Do nothing
                }
            })
        }
    }
}
