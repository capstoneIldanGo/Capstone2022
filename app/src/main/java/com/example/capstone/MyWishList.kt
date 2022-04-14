package com.example.capstone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.databinding.ActivityWishListBinding


class MyWishList : AppCompatActivity() {

    private var _binding: ActivityWishListBinding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel:MyWishListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWishListBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MyWishListViewModel::class.java)
    }

    // 어댑터 연결

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}