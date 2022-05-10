package com.ildango.capstone.resultdetail

import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ildango.capstone.R

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var iv_thumbnail = itemView.findViewById<ImageView>(R.id.ivProductThumbnail)
    var tv_title = itemView.findViewById<TextView>(R.id.tvWishlistTitle)
    var tv_price = itemView.findViewById<TextView>(R.id.tvWishlistPrice)

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
}