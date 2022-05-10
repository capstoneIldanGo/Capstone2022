package com.ildango.capstone.mypages.mywishlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MyWishListViewModel(private val wishListRepository: MyWishListRepository) : ViewModel() {
    val items : MutableLiveData<Response<List<MyWishItem>>> = MutableLiveData()

    fun getData() {
        viewModelScope.launch {
            val response = wishListRepository.getWishItem()
            items.value = response
        }
    }

    fun deleteItems() {

    }
}

class MyWishListViewModelFactory (private val repository: MyWishListRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyWishListViewModel(repository) as T
    }
}