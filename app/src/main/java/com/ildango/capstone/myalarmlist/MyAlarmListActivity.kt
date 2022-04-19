package com.ildango.capstone.myalarmlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ildango.capstone.databinding.ActivityPriceAlarmBinding

class MyAlarmListActivity : AppCompatActivity() {

    private var _binding: ActivityPriceAlarmBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPriceAlarmBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}