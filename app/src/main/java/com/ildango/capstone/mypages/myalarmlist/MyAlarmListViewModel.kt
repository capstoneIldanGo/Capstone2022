package com.ildango.capstone.mypages.myalarmlist

import androidx.lifecycle.*
import com.ildango.capstone.data.model.MyAlarmItem
import com.ildango.capstone.data.model.MyAlarmPostItem
import com.ildango.capstone.data.repository.MyAlarmListRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MyAlarmListViewModel(private val alarmListRepository: MyAlarmListRepository):ViewModel() {
    private var itemList = mutableListOf<MyAlarmItem>()
    private var _items : MutableLiveData<List<MyAlarmItem>> = MutableLiveData()
    val items: LiveData<List<MyAlarmItem>> = _items

    fun getData() {
        viewModelScope.launch {
            alarmListRepository.getAlarmItem()
                .onSuccess {
                    itemList.clear()
                    itemList.addAll(it)
                    _items.value = itemList
                }
        }
    }

    fun addAlarmItem(item: MyAlarmPostItem) {
        viewModelScope.launch {
            alarmListRepository.addAlarmItem(item)
        }
    }
}

class MyAlarmListViewModelFactory (private val repository: MyAlarmListRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyAlarmListViewModel(repository) as T
    }
}