package com.ildango.capstone.productdetail

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ildango.capstone.R
import com.ildango.capstone.data.repository.MyWishListRepository
import com.ildango.capstone.databinding.ActivityProductDetailBinding
import com.ildango.capstone.mypages.mywishlist.MyWishListViewModel
import com.ildango.capstone.mypages.mywishlist.MyWishListViewModelFactory
import com.ildango.capstone.mypages.mywishlist.MyWishPostItem

class ProductDetailActivity : AppCompatActivity(){
    private var _binding : ActivityProductDetailBinding ?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyWishListViewModel
    private val repository = MyWishListRepository()
    private val viewModelFactory = MyWishListViewModelFactory(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyWishListViewModel::class.java)

        binding.activity = this@ProductDetailActivity
        setSupportActionBar(binding.bottomAppBar)

        binding.tvKeyword.text = getStringFromIntent("keyword")
        setWebView()
    }

    private fun setWishItem() {
        viewModel.setItem(MyWishPostItem(21, 45))
    }

    fun onShareBtnClick() {
        Toast.makeText(applicationContext, "share", Toast.LENGTH_SHORT).show()
    }

    fun onWishBtnClick() {
        Toast.makeText(applicationContext, "wish", Toast.LENGTH_SHORT).show()
        setWishItem()
    }

    private fun setWebView() {
        binding.webView.webViewClient = WebViewClient()
        // binding.webView.loadUrl("https://www.google.com/")
        binding.webView.loadUrl(getStringFromIntent("url"))
    }

    private fun getStringFromIntent(keyword:String) : String {
        return intent.getStringExtra(keyword).toString()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}