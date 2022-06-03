package com.ildango.capstone.myinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ildango.capstone.databinding.ActivityGetInfoBinding

class GetInfoActivity : AppCompatActivity() {

    private var _binding: ActivityGetInfoBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityGetInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}