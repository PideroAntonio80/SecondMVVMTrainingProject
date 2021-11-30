package com.example.secondmvvmtrainingproject.presentation.pokemongame.view.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.secondmvvmtrainingproject.databinding.FragmentOptionsBinding
import com.example.secondmvvmtrainingproject.presentation.biometricauth.BiometricAuthActivity
import com.example.secondmvvmtrainingproject.presentation.secretroomar.constants.Constants.BULBASAUR
import com.example.secondmvvmtrainingproject.presentation.secretroomar.constants.Constants.EEVEE
import com.example.secondmvvmtrainingproject.presentation.secretroomar.constants.Constants.MEW
import com.example.secondmvvmtrainingproject.presentation.secretroomar.constants.Constants.PIKACHU
import com.example.secondmvvmtrainingproject.presentation.secretroomar.constants.Constants.POKEBALL
import com.example.secondmvvmtrainingproject.presentation.secretroomar.constants.Constants.PONYTA

class OptionsFragment : Fragment() {

    private var binding: FragmentOptionsBinding? = null

    private var option = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOptionsBinding.inflate(inflater, container, false)
        binding?.let {
            return it.root
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.animationView?.playAnimation()

        initOptions()
    }

    private fun initOptions() {
        binding?.let { binding ->
            binding.bContinue.setOnClickListener {
                when {
//                    binding.rbBabyElephant.isChecked -> option = BABY_ELEPHANT
//                    binding.rbCow.isChecked -> option = COW
                    binding.rbPikachu.isChecked -> option = PIKACHU
                    binding.rbMew.isChecked -> option = MEW
                    binding.rbPonyta.isChecked -> option = PONYTA
                    binding.rbBulbasaur.isChecked -> option = BULBASAUR
                    binding.rbEevee.isChecked -> option = EEVEE
                    binding.rbPokemonBall.isChecked -> option = POKEBALL
                }
                val intent = Intent(activity, BiometricAuthActivity::class.java)
                intent.putExtra("my_option", option)
                startActivity(intent)
                onDestroy()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        activity?.finish()
    }
}