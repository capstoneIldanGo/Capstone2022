package com.ildango.capstone.mypages.mywishlist

import com.ildango.capstone.service.ProductItem

class MyWishItemModel:ArrayList<MyWishItem>()

data class MyWishItem (
    val myPostId:Long,
    val post:ProductItem
)