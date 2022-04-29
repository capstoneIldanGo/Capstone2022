package com.ildango.capstone

import android.content.Intent
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

        val intent = intent
        var searchKeyword = intent.getStringExtra("keyword").toString()
        binding.tvSearchbar.text = searchKeyword

        var type:String = intent.getStringExtra("type").toString()
        setTextByType(type)

        binding.tvSearchbar.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setTextByType(type:String) {
        when(type){
            type1->
                binding.recentTransaction.text = "$type1 최저가"
            type2->
                binding.recentTransaction.text = "$type2 최저가"
            type3->
                binding.recentTransaction.text = "$type3 최저가"
        }
    }

}