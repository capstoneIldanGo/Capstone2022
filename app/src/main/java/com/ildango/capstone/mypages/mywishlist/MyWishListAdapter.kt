package com.ildango.capstone.mypages.mywishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ildango.capstone.R
import retrofit2.Response

class MyWishListAdapter(private var items: MutableLiveData<Response<List<MyWishItem>>>)
    : RecyclerView.Adapter<MyWishListAdapter.MyWishListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWishListViewHolder {
        return MyWishListViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MyWishListViewHolder, position: Int) {
        items.value!!.body()!!.get(position).let { item ->
            with(holder) {
                iv_thumbnail.setImageResource(R.drawable.logo)  //productImage
                tv_title.text = item.post.title
                tv_price.text = "${item.post.price}원"
            }
        }
    }

    override fun getItemCount(): Int {
        return items.value!!.body()!!.size
    }

    inner class MyWishListViewHolder constructor(parent:ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_wish_listview, parent, false)
    ) {
        var iv_thumbnail = itemView.findViewById<ImageView>(R.id.iv_wishlist_thumbnail)
        var tv_title = itemView.findViewById<TextView>(R.id.tv_wishlist_title)
        var tv_price = itemView.findViewById<TextView>(R.id.tv_wishlist_price)
    }

}
