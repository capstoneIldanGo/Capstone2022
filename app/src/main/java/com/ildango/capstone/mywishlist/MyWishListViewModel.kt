package com.ildango.capstone.mywishlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData


class MyWishListViewModel(application:Application) : AndroidViewModel(application) {

    var liveData : MutableLiveData<ArrayList<MyWishItem>> = MutableLiveData<ArrayList<MyWishItem>>()

    init {
        var WishItem = ArrayList<MyWishItem>()
        // dummy data
        WishItem.add(MyWishItem("imgUrl", "test1", "100000원"))
        WishItem.add(MyWishItem("imgUrl", "test2", "40000000원"))
        WishItem.add(MyWishItem("imgUrl", "test3", "25000원"))

        liveData.postValue(WishItem)
    }

    // 찜 목록에서 아이템 선택해서 삭제
    fun deleteItems() {

    }
}