package com.ildango.capstone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ildango.capstone.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private var _binding: ActivitySearchBinding ?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivitySearchBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

    }
}