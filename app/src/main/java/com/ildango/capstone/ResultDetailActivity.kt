package com.ildango.capstone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ildango.capstone.databinding.ActivitySearchDetailBinding

class ResultDetailActivity : AppCompatActivity(){

    private var _binding: ActivitySearchDetailBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}