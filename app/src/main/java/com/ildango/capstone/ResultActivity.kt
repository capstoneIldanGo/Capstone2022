package com.ildango.capstone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ildango.capstone.databinding.ActivitySearchResultBinding

const val type1 = "내 주변"
const val type2 = "전국"
const val type3 = "미개봉/S급"

class ResultActivity : AppCompatActivity() {

    private var _binding: ActivitySearchResultBinding?= null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        var searchKeyword = intent.getStringExtra("keyword").toString()
        binding.tvSearchbar.text = searchKeyword


        binding.tvSearchbar.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        binding.btnAroundLowest.setOnClickListener{
            val nextIntent = Intent(this, ResultDetailActivity::class.java)
            nextIntent.putExtra("keyword", searchKeyword)
            nextIntent.putExtra("type", type1)
            startActivity(nextIntent)
        }

        binding.btnWholeLowest.setOnClickListener{
            val nextIntent = Intent(this, ResultDetailActivity::class.java)
            nextIntent.putExtra("type", type2)
            startActivity(nextIntent)
        }

        binding.btnUnopenedLowest.setOnClickListener{
            val nextIntent = Intent(this, ResultDetailActivity::class.java)
            nextIntent.putExtra("type", type3)
            startActivity(nextIntent)
        }
    }


}