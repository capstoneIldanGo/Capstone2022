package com.ildango.capstone.result

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ResultViewModel(application: Application) : AndroidViewModel(application)  {

    private val priceListPrice = arrayListOf(18000, 18000, 18000)        // dummy data - 데이터 연결하면 따로 계산해서 넣을 것 !!
    private val twoWeeksChartData = arrayOf(700,300,200,1200,500,200,500)
    private val oneWeekChartData = arrayOf(700,300,200,1200,500,200,500)

    fun getPricesByTag(): ArrayList<Int> {
        return priceListPrice
    }

    // Chart 관련
    fun getTwoWeeksChartData() : Array<Int> { return twoWeeksChartData }
    fun getOneWeekChartData() : Array<Int> { return oneWeekChartData }

}