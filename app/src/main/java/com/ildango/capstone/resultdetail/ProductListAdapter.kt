package com.ildango.capstone.resultdetail

import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ildango.capstone.R
import retrofit2.Response

class ProductListAdapter(private val items: MutableLiveData<Response<ProductItemList>>)
    : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        items.value!!.body()!!.productList.let { item ->
            with(holder) {
                iv_thumbnail.setImageResource(R.drawable.logo)
                tv_title.text = item.get(position).title
                tv_price.text = "${item.get(position).price}원"
            }
        }
    }

    override fun getItemCount(): Int {
        return items.value!!.body()!!.productList.size
    }
}