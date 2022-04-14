package com.example.capstone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstone.databinding.ActivityWishListBinding

class MyWishList : AppCompatActivity() {

    private var _binding: ActivityWishListBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWishListBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
    }

}