package com.ildango.capstone.mypages.mywishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.ildango.capstone.R

class MyWishListAdapter(private var items: LiveData<ArrayList<MyWishItem>>)
    : RecyclerView.Adapter<MyWishListAdapter.MyWishListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWishListViewHolder {
        return MyWishListViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MyWishListViewHolder, position: Int) {
        items.value!!.get(position).let { item ->
            with(holder) {
                iv_thumbnail.setImageResource(R.drawable.logo)
                //tv_title.text = item.title
                //tv_price.text = item.price
            }
        }
    }

    override fun getItemCount(): Int {
        return items.value!!.size
    }

    inner class MyWishListViewHolder constructor(parent:ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_wish_listview, parent, false)
    ) {
        var iv_thumbnail = itemView.findViewById<ImageView>(R.id.iv_wishlist_thumbnail)
        var tv_title = itemView.findViewById<TextView>(R.id.tv_wishlist_title)
        var tv_price = itemView.findViewById<TextView>(R.id.tv_wishlist_price)
    }

}
