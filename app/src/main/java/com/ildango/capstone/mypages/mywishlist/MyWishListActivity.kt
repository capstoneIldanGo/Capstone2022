package com.ildango.capstone.mypages.mywishlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.ildango.capstone.data.repository.MyWishListRepository
import com.ildango.capstone.databinding.ActivityWishListBinding
import com.ildango.capstone.productdetail.ProductDetailActivity
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

        setRecyclerview()
        setObserver()
        setItemSwipe()
        setItemClickListener()
    }

    private fun setItemSwipe() {
        val swipeItemCallback = SwipeItemCallback(this, adapter).apply {
            setClamp(resources.displayMetrics.widthPixels.toFloat() / 5)
        }
        ItemTouchHelper(swipeItemCallback).attachToRecyclerView(binding.recyclerviewWishList)
        binding.recyclerviewWishList.setOnTouchListener { _, motionEvent ->
            val removedPos = swipeItemCallback.onDeleteIcon(motionEvent)
            if(removedPos != -1) viewModel.deleteItem(1, removedPos)
            swipeItemCallback.removePreviousClamp(binding.recyclerviewWishList)
            false
        }
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

    private fun setItemClickListener() {
        adapter.setItemClickListener(object : ProductViewHolder.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                Intent(this@MyWishListActivity, ProductDetailActivity::class.java).apply {
                    putExtra("keyword", "")
                    putExtra("postid", viewModel.getId(position))
                    putExtra("url", viewModel.getUrl(position))
                }.run { startActivity(this) }
            }
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
