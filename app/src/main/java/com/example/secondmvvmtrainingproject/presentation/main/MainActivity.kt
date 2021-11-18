package com.example.secondmvvmtrainingproject.presentation.main

import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.secondmvvmtrainingproject.R
import com.example.secondmvvmtrainingproject.databinding.ActivityMainBinding
import com.example.secondmvvmtrainingproject.presentation.main.fragment.ProfileFragment
import com.example.secondmvvmtrainingproject.presentation.main.utils.MainAux
import com.example.secondmvvmtrainingproject.presentation.pokemonbooklist.view.PokemonBookActivity
import com.example.secondmvvmtrainingproject.presentation.pokemongame.view.PokemonGameActivity
import com.example.secondmvvmtrainingproject.presentation.pokemonteam.view.PokemonTeamActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.security.MessageDigest

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainAux {

    private lateinit var binding: ActivityMainBinding

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    private lateinit var progress: LinearLayout
    private lateinit var topBar: AppBarLayout
    private lateinit var background: ConstraintLayout

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val response = IdpResponse.fromResultIntent(it.data)

        if (it.resultCode == RESULT_OK) {
            val user = FirebaseAuth.getInstance().currentUser
            if(user != null) {
                Toast.makeText(this, R.string.auth_wellcome, Toast.LENGTH_SHORT).show()
            }
        } else {
            if (response == null) {
                Toast.makeText(this, R.string.auth_see_you, Toast.LENGTH_SHORT).show()
                finish()
            } else {
                response.error?.let {
                    if (it.errorCode == ErrorCodes.NO_NETWORK) {
                        Toast.makeText(this, R.string.error_no_network, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "${R.string.error_code} ${it.errorCode}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configAuth()

        initViews()

    }

    private fun configAuth() {
        firebaseAuth = FirebaseAuth.getInstance()
        authStateListener = FirebaseAuth.AuthStateListener { auth ->
            if (auth.currentUser != null) {
                //supportActionBar?.title = auth.currentUser?.displayName
                updateTitle(auth.currentUser!!)

                progress.visibility = View.GONE
                topBar.visibility = View.VISIBLE
                background.visibility = View.VISIBLE
            } else {
                val providers = arrayListOf(
                    AuthUI.IdpConfig.EmailBuilder().build(),
                    AuthUI.IdpConfig.GoogleBuilder().build(),
                    AuthUI.IdpConfig.FacebookBuilder().build(),
                    AuthUI.IdpConfig.PhoneBuilder().build())

                resultLauncher.launch(
                    AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setIsSmartLockEnabled(false)
                    .build())
            }
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val info = getPackageManager().getPackageInfo(
                    "com.example.secondmvvmtrainingproject",
                    PackageManager.GET_SIGNING_CERTIFICATES)
                for (signature in info.signingInfo.apkContentsSigners) {
                    val md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    Log.d("API >= 28 KeyHash:",
                        Base64.encodeToString(md.digest(), Base64.DEFAULT))
                }
            } else {
                val info = getPackageManager().getPackageInfo(
                    "com.example.secondmvvmtrainingproject",
                    PackageManager.GET_SIGNATURES)
                for (signature in info.signatures) {
                    val md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    Log.d("API < 28 KeyHash:",
                        Base64.encodeToString(md.digest(), Base64.DEFAULT))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onResume() {
        super.onResume()
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    override fun onPause() {
        super.onPause()
        firebaseAuth.removeAuthStateListener(authStateListener)
    }

    private fun initViews() {
        progress = findViewById(R.id.llProgress)
        topBar = findViewById(R.id.appBar)
        background = findViewById(R.id.clBackground)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = binding.dlDrawerLayout

        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        binding.nvNavigationView.setNavigationItemSelectedListener(this)

        //actionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.pokemon_book -> startActivity(Intent(this, PokemonBookActivity::class.java))
            R.id.my_team -> startActivity(Intent(this, PokemonTeamActivity::class.java))
            R.id.play_game -> startActivity(Intent(this, PokemonGameActivity::class.java))
            R.id.my_records -> Toast.makeText(this, "my_records", Toast.LENGTH_SHORT).show()
            R.id.action_profile -> {
                val fragment = ProfileFragment()
                supportFragmentManager.beginTransaction().add(R.id.dlDrawerLayout, fragment).addToBackStack(null).commit()
            }
            R.id.close_session -> AuthUI.getInstance().signOut(this)
                .addOnSuccessListener {
                    Toast.makeText(this, R.string.finish_session, Toast.LENGTH_SHORT).show()
                }
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        progress.visibility = View.VISIBLE
                        topBar.visibility = View.GONE
                        background.visibility = View.GONE
                    } else {
                        Toast.makeText(this, R.string.error_closing_session, Toast.LENGTH_SHORT).show()
                    }
                }
            R.id.contact_us -> Toast.makeText(this, "contact_us", Toast.LENGTH_SHORT).show()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun updateTitle(user: FirebaseUser) {
        supportActionBar?.title = user.displayName
    }
}
