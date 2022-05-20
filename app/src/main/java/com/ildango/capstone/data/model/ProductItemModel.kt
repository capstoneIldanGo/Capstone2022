package com.ildango.capstone.resultdetail

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

class ProductItemModel:ArrayList<ProductItem>()

data class ProductItemList(
    @SerializedName("content")
    val productList : List<ProductItem>,
)

data class ProductItem (
    // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함
    // @SerializedName()로 변수명을 일치시켜주면 클래스 변수명이 달라도 알아서 매핑

    val postId : Long,
    val url : String,
    val isSold : Boolean,
    val isMint : Boolean,
    val uploadDate : String,    // LocalDateTime으로 변환과정 추가 필요함
    val location : LocationItem,
    val platform : String,
    val price : Int,
    val itemName : String,
    val title : String,
    val productImage : String
)

data class LocationItem (
    val locationId: Long,
    val city: String,
    val state: String
)