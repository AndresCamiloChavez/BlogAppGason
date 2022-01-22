package com.devandreschavez.blogappgason.viewModel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.devandreschavez.blogappgason.core.Resource
import com.devandreschavez.blogappgason.domain.home.HomeRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class HomeViewModel(private val homeRepository: HomeRepository): ViewModel() {
    fun fetchPost() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(homeRepository.getLatestPosts())
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}

class FactoryHomeViewModel(private val homeRepository: HomeRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HomeRepository::class.java).newInstance(homeRepository)
    }

}