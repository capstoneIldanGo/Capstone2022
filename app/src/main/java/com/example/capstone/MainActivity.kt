package com.example.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.LinearLayout
import androidx.core.view.MotionEventCompat
import com.example.capstone.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding ?= null
    private val binding get() = _binding!!
    private val bottomSheet = BottomSheetFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {

        }

        binding.viewUnderSearchBar.setOnTouchListener(object:OnSwipeTouchListener(this@MainActivity) {
            override fun onSwipeTop() {
                bottomSheet.show(supportFragmentManager, BottomSheetFragment.TAG)
            }
        })

    }

}