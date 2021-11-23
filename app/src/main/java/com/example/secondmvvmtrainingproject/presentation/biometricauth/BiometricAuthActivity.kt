package com.example.secondmvvmtrainingproject.presentation.biometricauth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.secondmvvmtrainingproject.R
import com.example.secondmvvmtrainingproject.databinding.ActivityBiometricAuthBinding
import com.example.secondmvvmtrainingproject.presentation.secretroomar.view.SecretRoomARActivity
import java.util.concurrent.Executor

class BiometricAuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBiometricAuthBinding

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private var myPrizeUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiometricAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle != null) {
            myPrizeUrl = bundle.getString("my_option").toString()
        }

        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(this, executor, object: BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                binding.tvAuthStatus.text = "Error: $errString"
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                binding.tvAuthStatus.text = getString(R.string.success_auth_text)
                val intent = Intent(this@BiometricAuthActivity, SecretRoomARActivity::class.java)
                intent.putExtra("option", myPrizeUrl)
                startActivity(intent)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                binding.tvAuthStatus.text = getString(R.string.failed_auth_text)
            }
        })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Login using fingerprint or face id")
            .setNegativeButtonText("Cancel")
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.BIOMETRIC_WEAK)
            .build()

        if (biometricsAvailable()) {
            binding.bAuth.setOnClickListener {
                biometricPrompt.authenticate(promptInfo)
            }
        }
    }

    private fun biometricsAvailable(): Boolean {
        val biometricManager = BiometricManager.from(this)
        return when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.BIOMETRIC_WEAK)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.")
                true
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Log.d("MY_APP_TAG", "No biometric features available on this device.")
                false
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Log.d("MY_APP_TAG", "Biometric features are currently unavailable.")
                false
            }
            else -> false
        }
    }
}