package com.devandreschavez.blogappgason.viewModel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.devandreschavez.blogappgason.core.Resource
import com.devandreschavez.blogappgason.domain.auth.LoginRepository
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val authRepository: LoginRepository) : ViewModel() {

    fun signIn(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(authRepository.signIn(email, password)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class FactoryLoginViewModel(private val authRepository: LoginRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return LoginViewModel(authRepository) as T
//        Otra forma de realizar el factory
//        return modelClass.getConstructor(LoginRepository::class.java).newInstance(authRepository)
    }

}