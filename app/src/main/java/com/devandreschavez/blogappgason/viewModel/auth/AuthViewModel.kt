package com.devandreschavez.blogappgason.viewModel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.devandreschavez.blogappgason.core.Result
import com.devandreschavez.blogappgason.domain.auth.AuthRepository
import kotlinx.coroutines.Dispatchers

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    fun signIn(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Success(authRepository.signIn(email, password)))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }
    fun signUp(email: String, password: String, username: String) = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Success(authRepository.signUp(email, password, username)))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }
}

class FactoryAuthViewModel(private val authRepository: AuthRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return AuthViewModel(authRepository) as T
//        Otra forma de realizar el factory
//        return modelClass.getConstructor(LoginRepository::class.java).newInstance(authRepository)
    }

}