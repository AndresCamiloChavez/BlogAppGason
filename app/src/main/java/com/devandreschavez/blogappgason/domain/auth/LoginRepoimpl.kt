package com.devandreschavez.blogappgason.domain.auth

import com.devandreschavez.blogappgason.data.remote.auth.AuthDataSource
import com.google.firebase.auth.FirebaseUser

class LoginRepoimpl(private val loginDataSource: AuthDataSource): LoginRepository{
    override suspend fun signIn(email: String, password: String): FirebaseUser? = loginDataSource.signIn(email, password)
}