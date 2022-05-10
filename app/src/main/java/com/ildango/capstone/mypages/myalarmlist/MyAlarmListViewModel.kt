package com.ildango.capstone.mypages.myalarmlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MyAlarmListViewModel(private val alarmListRepository: MyAlarmListRepository):ViewModel() {
    val items: MutableLiveData<Response<List<MyAlarmItem>>> = MutableLiveData()

    fun getData() {
        viewModelScope.launch {
            val response = alarmListRepository.getAlarmItem()
            items.value = response
        }
    }

    fun deleteItems() {

    }
}

class MyAlarmListViewModelFactory (private val repository: MyAlarmListRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyAlarmListViewModel(repository) as T
    }
}