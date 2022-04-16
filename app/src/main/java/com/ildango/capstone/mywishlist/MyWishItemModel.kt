package com.ildango.capstone.mywishlist

class MyWishItemModel:ArrayList<MyWishItem>()

data class MyWishItem (
    var thumbnailImg : String,
    var title : String,
    var price : String
)