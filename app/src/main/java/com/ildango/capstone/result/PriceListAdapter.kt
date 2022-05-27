package com.ildango.capstone.result

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ildango.capstone.R


class PriceListAdapter(private val listTag:ArrayList<String>) : BaseAdapter() {

    private var price1 = MutableLiveData<Int>()
    private var price2 = MutableLiveData<Int>()
    private var price3 = MutableLiveData<Int>()

    private lateinit var tv_price:TextView

    fun setPrice1(price:Int) {
        price1.value = price
        notifyDataSetChanged()
    }
    fun setPrice2(price:Int) {
        price2.value = price
        notifyDataSetChanged()
    }
    fun setPrice3(price:Int) {
        price3.value = price
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return listTag.size
    }

    override fun getItem(p0: Int): Any {
        return listTag[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = p2!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_price_list_listview, p2, false)

        val tv_tag = view.findViewById<TextView>(R.id.tv_pricelist_tag)
        tv_price = view.findViewById<TextView>(R.id.tv_pricelist_price)

        tv_tag.text = "${listTag[p0]} 최저가는"
        setPriceText(p0)

        return view
    }

    private fun setPriceText(pos:Int) {
        when(pos) {
            0-> tv_price.text = "${price1.value}원"
            1-> tv_price.text = "${price2.value}원"
            2-> tv_price.text = "${price3.value}원"
        }
    }

}