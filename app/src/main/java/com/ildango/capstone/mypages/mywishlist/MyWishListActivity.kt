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
import com.ildango.capstone.resultdetail.ProductViewHolder


class MyWishListActivity : AppCompatActivity() {

    private var _binding: ActivityWishListBinding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyWishListViewModel
    private val repository = MyWishListRepository()
    private val viewModelFactory = MyWishListViewModelFactory(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWishListBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyWishListViewModel::class.java)

        // list view
        binding.recyclerviewWishList.layoutManager = LinearLayoutManager(this)

        viewModel.getData()
        setObserver()
    }

    private fun setObserver() {
        viewModel.items.observe(this, Observer {
            if(it.isSuccessful) {
                val adapter = MyWishListAdapter(viewModel.items)
                binding.recyclerviewWishList.adapter = adapter
                adapter.setItemClickListener(object : ProductViewHolder.OnItemClickListener {
                    override fun onClick(v: View, position: Int) {
                        Intent(this@MyWishListActivity, ProductDetailActivity::class.java).apply {
                            putExtra("keyword", "")
                            putExtra("url", viewModel.getUrl(position))
                        }.run { startActivity(this) }
                    }
                })
            }
            else {
                Log.d("Response", "ERROR:${it.errorBody().toString()}")
            }
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
