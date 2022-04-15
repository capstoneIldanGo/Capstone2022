package com.ildango.capstone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ildango.capstone.databinding.SearchResultBinding

class ResultActivity : AppCompatActivity() {

    private var _binding: SearchResultBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = SearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}