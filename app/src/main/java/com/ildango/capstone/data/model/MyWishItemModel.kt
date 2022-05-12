package com.ildango.capstone.mypages.mywishlist

import com.ildango.capstone.resultdetail.ProductItem

class MyWishItemModel:ArrayList<MyWishItem>()

data class MyWishItem (
    val myPostId:Long,
    val post: ProductItem
)