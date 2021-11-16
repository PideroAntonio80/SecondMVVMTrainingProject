package com.example.secondmvvmtrainingproject.presentation.main.utils

import com.google.firebase.auth.FirebaseUser

interface MainAux {
    //fun showButton(isVisible: Boolean)
    fun updateTitle(user: FirebaseUser)
}