package com.ildango.capstone.myinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ildango.capstone.data.repository.LocationRepository
import kotlinx.coroutines.launch

class GetInfoViewModel(private val locationRepository: LocationRepository): ViewModel() {
    var cityList = MutableLiveData<List<String>>()
    var stateList = MutableLiveData<List<String>>()

    init {
        viewModelScope.launch {
            locationRepository.getCities()
                .onSuccess {
                    cityList.value = it
                }
        }
    }

    fun getStates(state:String) {
        viewModelScope.launch {
            locationRepository.getStates(state)
                .onSuccess {
                    stateList.value = it
                }
        }
    }

}

class GetInfoViewModelFactory(private val repository: LocationRepository)
    :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GetInfoViewModel(repository) as T
    }

}