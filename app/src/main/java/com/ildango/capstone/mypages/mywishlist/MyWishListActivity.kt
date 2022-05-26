package com.ildango.capstone.mypages.mywishlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ildango.capstone.databinding.ActivityWishListBinding
import com.ildango.capstone.productdetail.ProductDetailActivity
import com.ildango.capstone.data.repository.MyWishListRepository
import com.ildango.capstone.resultdetail.ProductViewHolder

class MyWishListActivity : AppCompatActivity() {

    private var _binding: ActivityWishListBinding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyWishListViewModel
    private val repository = MyWishListRepository()
    private val viewModelFactory = MyWishListViewModelFactory(repository)
    private lateinit var adapter: MyWishListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWishListBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyWishListViewModel::class.java)

        // list view
        setRecyclerview()
        setObserver()
    }

    private fun setRecyclerview() {
        binding.recyclerviewWishList.layoutManager = LinearLayoutManager(this)
        adapter = MyWishListAdapter()
        binding.recyclerviewWishList.adapter = adapter
        viewModel.getData()
    }

    private fun setObserver() {
        viewModel.items.observe(this, Observer {
            adapter.setItems(it)
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
