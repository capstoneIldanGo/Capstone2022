package com.ildango.capstone.result

class ChartItemModel : ArrayList<ChartItem>()

data class ChartItem(
    val avgPrice: Int,
    val date : String
)