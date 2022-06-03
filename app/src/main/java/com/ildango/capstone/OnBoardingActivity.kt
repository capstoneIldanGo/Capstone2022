package com.ildango.capstone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ildango.capstone.databinding.ActivityOnboardingImageBinding
import com.ildango.capstone.myinfo.GetInfoActivity

class OnBoardingActivity : AppCompatActivity() {

    private var _binding : ActivityOnboardingImageBinding ?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOnboardingImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartGetInfo.setOnClickListener {
            finish()
            val intent = Intent(this@OnBoardingActivity, GetInfoActivity::class.java)
            startActivity(intent)
        }
    }
}