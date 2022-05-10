package com.ildango.capstone.resultdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ildango.capstone.R

class ProductViewHolder constructor(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_product_listview, parent, false)
) {
    var iv_thumbnail = itemView.findViewById<ImageView>(R.id.ivProductThumbnail)
    var tv_title = itemView.findViewById<TextView>(R.id.tvWishlistTitle)
    var tv_price = itemView.findViewById<TextView>(R.id.tvWishlistPrice)
}