package com.ildango.capstone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
        binding.searchView.setQuery(searchKeyword, false)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                finish()
                var intent = Intent(this@ResultActivity, ResultActivity::class.java)
                intent.putExtra("keyword", binding.searchView.query.toString())
                startActivity(intent)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                // 텍스트 값 바뀔 때
                return false
            }
        })

        binding.btnAroundLowest.setOnClickListener{
            val nextIntent = Intent(this, ResultDetailActivity::class.java)
            nextIntent.putExtra("keyword", searchKeyword)
            nextIntent.putExtra("type", type1)
            startActivity(nextIntent)
        }

        binding.btnWholeLowest.setOnClickListener{
            val nextIntent = Intent(this, ResultDetailActivity::class.java)
            nextIntent.putExtra("keyword", searchKeyword)
            nextIntent.putExtra("type", type2)
            startActivity(nextIntent)
        }

        binding.btnUnopenedLowest.setOnClickListener{
            val nextIntent = Intent(this, ResultDetailActivity::class.java)
            nextIntent.putExtra("keyword", searchKeyword)
            nextIntent.putExtra("type", type3)
            startActivity(nextIntent)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}