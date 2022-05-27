package com.ildango.capstone.productdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ildango.capstone.R
import com.ildango.capstone.data.repository.MyWishListRepository
import com.ildango.capstone.databinding.ActivityProductDetailBinding
import com.ildango.capstone.mypages.mywishlist.MyWishListViewModel
import com.ildango.capstone.mypages.mywishlist.MyWishListViewModelFactory
import com.ildango.capstone.data.model.MyWishPostItem

class ProductDetailActivity : AppCompatActivity() {
    private var _binding: ActivityProductDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyWishListViewModel
    private val repository = MyWishListRepository()
    private val viewModelFactory = MyWishListViewModelFactory(repository)

    private var isExistsInWishList:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyWishListViewModel::class.java)

        binding.activity = this@ProductDetailActivity
        setSupportActionBar(binding.bottomAppBar)

        binding.tvKeyword.text = getStringFromIntent("keyword")
        setWishBtnImage()
        setWebView()
    }

    private fun setWishBtnImage() {
        viewModel.isItemExistInMyPosts(1, intent.getLongExtra("postid", 0)).observe(this, Observer {
            if (it) {
                isExistsInWishList = true
                binding.imgBtnWish.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
            else {
                isExistsInWishList = false
                binding.imgBtnWish.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        })
    }

    fun onShareBtnClick() {
        Toast.makeText(applicationContext, "share", Toast.LENGTH_SHORT).show()
    }

    fun onWishBtnClick() {
        if(isExistsInWishList) {
            // delete from wish lists
            // isExistsInWishList = false
            // binding.imgBtnWish.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
        else {
            addWishItem()
            isExistsInWishList = true
            binding.imgBtnWish.setImageResource(R.drawable.ic_baseline_favorite_24)
        }
    }

    private fun addWishItem() {
        viewModel.addWishItem(MyWishPostItem(1, intent.getLongExtra("postid", 0)))
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        var url = getStringFromIntent("url")

        binding.webView.apply {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            setNetworkAvailable(true)
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            loadUrl(url)
        }
    }

    private fun getStringFromIntent(keyword: String): String {
        return intent.getStringExtra(keyword).toString()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}