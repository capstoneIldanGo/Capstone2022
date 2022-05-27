package com.ildango.capstone.data.model

class MyWishItemModel:ArrayList<MyWishItem>()

data class MyWishItem (
    val myPostId:Long,
    val post: ProductItem
)

data class MyWishPostItem (
    val userId:Long,
    val postId:Long
)