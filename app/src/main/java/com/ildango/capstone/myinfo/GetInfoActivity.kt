package com.ildango.capstone.myinfo

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginStart
import com.ildango.capstone.databinding.ActivityGetInfoBinding

class GetInfoActivity : AppCompatActivity() {

    private var _binding: ActivityGetInfoBinding?= null
    private val binding get() = _binding!!
    private var isOnboarding = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityGetInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isOnboarding = intent.getBooleanExtra("isFirst", false)

        changeBtnOnBoarding()
        setOnBtnClick()
    }

    private fun changeBtnOnBoarding() {
        if(isOnboarding) {
            binding.btnCancelInfo.visibility = View.GONE
        }
    }

    private fun setOnBtnClick() {
        binding.btnSubmitInfo.setOnClickListener {
            finish()
        }
        binding.btnCancelInfo.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {
        if(!isOnboarding)
            super.onBackPressed()
    }
}