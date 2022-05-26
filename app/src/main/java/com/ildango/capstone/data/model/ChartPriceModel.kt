package com.ildango.capstone.result


data class ChartPriceList(
    val chartPriceList : List<ChartPrice>
)

data class ChartPrice(
    val date : String,
    val avg: Int
)