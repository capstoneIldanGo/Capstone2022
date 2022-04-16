package com.ildango.capstone.mywishlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData


class MyWishListViewModel(application:Application) : AndroidViewModel(application) {

    var liveData : MutableLiveData<ArrayList<MyWishItem>> = MutableLiveData<ArrayList<MyWishItem>>()

    init {

    }

    // 찜 목록에서 아이템 선택해서 삭제
    fun deleteItems() {

    }
}