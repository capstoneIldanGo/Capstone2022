package com.ildango.capstone.resultdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ildango.capstone.R
import java.lang.Exception

class ProductListAdapter() : RecyclerView.Adapter<ProductViewHolder>() {

    private val items = mutableListOf<ProductItem>()
    private lateinit var itemClickListener : ProductViewHolder.OnItemClickListener

    fun setItems(items: List<ProductItem>) {
        this.items.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_listview, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        items.let { item ->
            with(holder) {
                try {
                    Glide.with(holder.itemView.context)
                        .load(item.get(position).productImage)
                        .override(100,100)
                        .fitCenter()
                        .into(iv_thumbnail)
                } catch (e:Exception) {
                    Glide.with(holder.itemView.context)
                        .load(R.drawable.logo)
                        .into(iv_thumbnail)
                }
                tv_title.text = item.get(position).title
                tv_price.text = "${item.get(position).price}원"
            }
        }
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItemClickListener(onItemClickListener: ProductViewHolder.OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

}