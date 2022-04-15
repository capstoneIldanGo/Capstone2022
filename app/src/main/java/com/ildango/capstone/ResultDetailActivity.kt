package com.ildango.capstone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ildango.capstone.databinding.SearchDetailBinding

class ResultDetailActivity : AppCompatActivity(){

    private var _binding: SearchDetailBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = SearchDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}