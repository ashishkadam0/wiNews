package com.kadamab.winews.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.kadamab.winews.repository.MainRepository
import com.kadamab.winews.utility.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    /**
     *   Get top News
     *   https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json
     */
    fun getNews() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getNews()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}