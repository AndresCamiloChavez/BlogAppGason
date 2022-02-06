package com.devandreschavez.blogappgason.data.remote.auth

import com.devandreschavez.blogappgason.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthDataSource {
    suspend fun signIn(email: String, password: String): FirebaseUser?{
        val authResult = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
        return authResult.user
    }

    suspend fun signUp(email: String, password: String, username: String): FirebaseUser? {
        val authResult = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await();
        authResult.user?.let { FirebaseFirestore.getInstance().collection("users").document(it.uid).set(User(username,email, "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png")).await() }
        return authResult.user
    }
}