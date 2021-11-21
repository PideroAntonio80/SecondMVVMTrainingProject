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
import com.example.secondmvvmtrainingproject.presentation.secretroomar.constants.Constants.BABY_ELEPHANT
import com.example.secondmvvmtrainingproject.presentation.secretroomar.constants.Constants.COW
import com.example.secondmvvmtrainingproject.presentation.secretroomar.constants.Constants.PIKACHU
import com.example.secondmvvmtrainingproject.presentation.secretroomar.constants.Constants.VOLTORB
import com.example.secondmvvmtrainingproject.presentation.secretroomar.view.SecretRoomARActivity

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
                    binding.rbBabyElephant.isChecked -> option = BABY_ELEPHANT
                    binding.rbCow.isChecked -> option = COW
                    binding.rbPikachu.isChecked -> option = PIKACHU
                    binding.rbPokemonBall.isChecked -> option = VOLTORB
                    binding.rbOtro.isChecked -> option = BABY_ELEPHANT
                    binding.rbOtroMas.isChecked -> option = COW
                }
                val intent = Intent(activity, SecretRoomARActivity::class.java)
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