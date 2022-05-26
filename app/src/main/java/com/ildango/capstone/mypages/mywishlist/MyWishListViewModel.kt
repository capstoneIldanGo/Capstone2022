package com.ildango.capstone.mypages.mywishlist

import androidx.lifecycle.*
import com.ildango.capstone.data.model.MyWishItem
import com.ildango.capstone.data.model.MyWishPostItem
import com.ildango.capstone.data.repository.MyWishListRepository
import kotlinx.coroutines.*
import retrofit2.Response

class MyWishListViewModel(private val wishListRepository: MyWishListRepository) : ViewModel() {
    private var itemList = mutableListOf<MyWishItem>()
    private var _items : MutableLiveData<List<MyWishItem>> = MutableLiveData()
    val items: LiveData<List<MyWishItem>> = _items

    fun getData() {
        viewModelScope.launch {
            wishListRepository.getWishItem()
                .onSuccess {
                    itemList.addAll(it)
                    _items.value = itemList
                }
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
        return items.value!!.get(pos).post.url
    }

    fun getId(pos: Int): Long {
        return items.value!!.get(pos).post.postId
    }

    fun addWishItem(item: MyWishPostItem) {
        viewModelScope.launch {
            wishListRepository.addWishItem(item)
                .onSuccess {
                }
                .onFailure {

                }
        }
    }
}

class MyWishListViewModelFactory(private val repository: MyWishListRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyWishListViewModel(repository) as T
    }
}