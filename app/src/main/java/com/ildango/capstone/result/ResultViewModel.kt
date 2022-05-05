package com.ildango.capstone.result

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ResultViewModel(application: Application) : AndroidViewModel(application)  {

    private val priceListPrice = arrayListOf(18000, 18000, 18000)        // dummy data - 데이터 연결하면 따로 계산해서 넣을 것 !!

    fun getPricesByTag(): ArrayList<Int> {
        return priceListPrice
    }
}