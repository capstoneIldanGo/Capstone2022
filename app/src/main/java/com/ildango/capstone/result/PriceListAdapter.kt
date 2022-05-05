package com.ildango.capstone.result

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ildango.capstone.R


class PriceListAdapter(listTag:ArrayList<String>, listPrice:ArrayList<Int>) : BaseAdapter() {

    private var listTag = ArrayList<String>()
    private var listPrice = ArrayList<Int>()

    init {
        this.listTag = listTag
        this.listPrice = listPrice
    }

    override fun getCount(): Int {
        return listTag.size
    }

    override fun getItem(p0: Int): Any {
        return listPrice[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = p2!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_price_list_listview, p2, false)

        val tv_tag = view.findViewById<TextView>(R.id.tv_pricelist_tag)
        val tv_price = view.findViewById<TextView>(R.id.tv_pricelist_price)

        tv_tag.text = "${listTag[p0]} 최저가는"
        tv_price.text = "${listPrice[p0]}원"

        return view
    }

}