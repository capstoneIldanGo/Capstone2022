package com.ildango.capstone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ildango.capstone.databinding.ActivitySearchResultBinding


class ResultActivity : AppCompatActivity() {

    private var _binding: ActivitySearchResultBinding?= null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAroundLowest.setOnClickListener{
            val nextIntent = Intent(this, ResultDetailActivity::class.java)
            startActivity(nextIntent)
        }

        binding.btnWholeLowest.setOnClickListener{
            val nextIntent = Intent(this, ResultDetailActivity::class.java)
            startActivity(nextIntent)
        }

        binding.btnUnopenedLowest.setOnClickListener{
            val nextIntent = Intent(this, ResultDetailActivity::class.java)
            startActivity(nextIntent)
        }
    }




}