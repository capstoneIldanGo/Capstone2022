package com.ildango.capstone.mypages.mywishlist

class MyWishItemModel:ArrayList<MyWishItem>()

data class MyWishItem (
    val thumbnailImg : String,
    val title : String,
    val price : String
)