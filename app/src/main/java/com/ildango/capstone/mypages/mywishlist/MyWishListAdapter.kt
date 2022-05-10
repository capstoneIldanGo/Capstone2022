package com.ildango.capstone.mypages.mywishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ildango.capstone.R
import com.ildango.capstone.resultdetail.ProductViewHolder
import retrofit2.Response

class MyWishListAdapter(private var items: MutableLiveData<Response<List<MyWishItem>>>)
    : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_listview, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
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

}
