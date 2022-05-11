package com.ildango.capstone.productdetail

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ildango.capstone.R
import com.ildango.capstone.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity(){
    private var _binding : ActivityProductDetailBinding ?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activity = this@ProductDetailActivity
        setSupportActionBar(binding.bottomAppBar)

        binding.tvKeyword.text = getStringFromIntent("keyword")
        setWebView()
    }

    fun onBtnClick() {
        Toast.makeText(applicationContext, "share", Toast.LENGTH_SHORT).show()
    }

    fun onWishBtnClick() {
        Toast.makeText(applicationContext, "wish", Toast.LENGTH_SHORT).show()
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