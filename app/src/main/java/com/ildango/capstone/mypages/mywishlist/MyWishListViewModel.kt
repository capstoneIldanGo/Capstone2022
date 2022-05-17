package com.ildango.capstone.mypages.mywishlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ildango.capstone.data.repository.MyWishListRepository
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

    fun getUrl(pos:Int): String {
        return items.value!!.body()!!.get(pos).post.url
    }

    fun getId(pos:Int): Long {
        return items.value!!.body()!!.get(pos).post.postId
    }


    fun setItem(item:MyWishPostItem) {
        viewModelScope.launch {
            wishListRepository.setWishItem(item)
                .onSuccess {
                //    getData()
                }
                .onFailure {

                }
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