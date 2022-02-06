package com.devandreschavez.blogappgason.domain.auth

import com.devandreschavez.blogappgason.data.remote.auth.AuthDataSource
import com.google.firebase.auth.FirebaseUser

class AuthRepoimpl(private val authDataSource: AuthDataSource): AuthRepository{
    override suspend fun signIn(email: String, password: String): FirebaseUser? = authDataSource.signIn(email, password)
    override suspend fun signUp(email: String, password: String, username: String): FirebaseUser? = authDataSource.signUp(email, password, username)
}