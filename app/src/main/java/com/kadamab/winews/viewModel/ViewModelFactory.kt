package com.kadamab.winews.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kadamab.winews.network.ApiHelper
import com.kadamab.winews.repository.MainRepository

/*
* ViewModelStore where ViewModels will be stored.
factory - factory a Factory which will be used to instantiate new ViewModels
* */
class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")    }

}