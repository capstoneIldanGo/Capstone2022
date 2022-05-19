package com.ildango.capstone.mypages.mywishlist

import android.util.Log
import androidx.lifecycle.*
import com.ildango.capstone.data.repository.MyWishListRepository
import kotlinx.coroutines.*
import retrofit2.Response

class MyWishListViewModel(private val wishListRepository: MyWishListRepository) : ViewModel() {
    val items: MutableLiveData<Response<List<MyWishItem>>> = MutableLiveData()

    fun getData() {
        viewModelScope.launch {
            val response = wishListRepository.getWishItem()
            items.value = response
        }
    }

    fun isItemExistInMyPosts(userId: Long, postId: Long): LiveData<Boolean> {
        val isExist = MutableLiveData<Boolean>()
        viewModelScope.launch {
            wishListRepository.isItemExistInMyPosts(userId, postId)
                .onSuccess {
                    isExist.postValue(it)
                }
        }
        return isExist
    }

    fun getUrl(pos: Int): String {
        return items.value!!.body()!!.get(pos).post.url
    }

    fun getId(pos: Int): Long {
        return items.value!!.body()!!.get(pos).post.postId
    }

    fun addWishItem(item: MyWishPostItem) {
        viewModelScope.launch {
            wishListRepository.addWishItem(item)
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

class MyWishListViewModelFactory(private val repository: MyWishListRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyWishListViewModel(repository) as T
    }
}