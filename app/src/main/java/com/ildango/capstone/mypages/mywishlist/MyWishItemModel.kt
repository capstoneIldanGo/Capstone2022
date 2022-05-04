package com.ildango.capstone.mypages.mywishlist

class MyWishItemModel:ArrayList<MyWishItem>()

data class MyWishItem (
    var thumbnailImg : String,
    var title : String,
    var price : String
)