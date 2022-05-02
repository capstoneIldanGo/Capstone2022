package com.ildango.capstone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ildango.capstone.databinding.ActivitySearchResultBinding

class ChartActivity : AppCompatActivity() {

    private var _binding: ActivitySearchResultBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val thread = ThreadClass()
        thread.start()
    }

    inner class ThreadClass : Thread() {
        override fun run() {
            val input = Array<Double>(200,{Math.random()})

            // 그래프 초기값 설정은 이곳에 //

            for (i in 0 until input.size){ // 그래프 표현식은 이곳에 //


            }
        }
    }
}
