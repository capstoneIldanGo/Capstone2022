package com.ildango.capstone.productdetail

import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.ildango.capstone.databinding.ActivityProductDetailBinding
import com.ildango.capstone.result.ResultActivity

class ProductDetailActivity : AppCompatActivity() {
    private var _binding : ActivityProductDetailBinding ?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSearchView()
        setWebView()
    }

    private fun setWebView() {
        binding.webView.webViewClient = WebViewClient()
        // binding.webView.loadUrl("https://www.google.com/")
        binding.webView.loadUrl(getStringFromIntent("url"))
    }

    private fun setSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                finish()
                var intent = Intent(this@ProductDetailActivity, ResultActivity::class.java)
                intent.putExtra("keyword", binding.searchView.query.toString())
                startActivity(intent)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                // 텍스트 값 바뀔 때
                return false
            }
        })

        binding.searchView.setQuery(getStringFromIntent("keyword"), false)
    }

    private fun getStringFromIntent(keyword:String) : String {
        return intent.getStringExtra(keyword).toString()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}