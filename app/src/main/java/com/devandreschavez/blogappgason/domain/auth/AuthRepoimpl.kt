package com.devandreschavez.blogappgason.domain.auth

import android.graphics.Bitmap
import com.devandreschavez.blogappgason.data.remote.auth.AuthDataSource
import com.google.firebase.auth.FirebaseUser

class AuthRepoimpl(private val authDataSource: AuthDataSource): AuthRepository{
    override suspend fun signIn(email: String, password: String): FirebaseUser? = authDataSource.signIn(email, password)
    override suspend fun signUp(email: String, password: String, username: String): FirebaseUser? = authDataSource.signUp(email, password, username)
    override suspend fun updateProfile(imageBitmap: Bitmap, username: String) = authDataSource.updateUserProfile(imageBitmap, username)
}