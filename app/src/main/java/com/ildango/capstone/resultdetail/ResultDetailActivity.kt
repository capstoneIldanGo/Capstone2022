package com.ildango.capstone.resultdetail

import SortingSheetFragment
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.ildango.capstone.databinding.ActivitySearchDetailBinding
import com.ildango.capstone.result.ResultActivity
import com.ildango.capstone.result.type1
import com.ildango.capstone.result.type2
import com.ildango.capstone.result.type3

class ResultDetailActivity : AppCompatActivity(){

    private var _binding: ActivitySearchDetailBinding?= null
    private val binding get() = _binding!!
    private val sortingSheet = SortingSheetFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSorting.setOnClickListener{
            sortingSheet.show(supportFragmentManager, SortingSheetFragment.TAG)
        }
        
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                finish()
                var intent = Intent(this@ResultDetailActivity, ResultActivity::class.java)
                intent.putExtra("keyword", binding.searchView.query.toString())
                startActivity(intent)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                // 텍스트 값 바뀔 때
                return false
            }
        })

        val intent = intent
        var searchKeyword = intent.getStringExtra("keyword").toString()
        binding.searchView.setQuery(searchKeyword, false)

        var type:String = intent.getStringExtra("type").toString()
        setTextByType(type)
    }

    private fun setTextByType(type:String) {
        when(type){
            type1 ->
                binding.recentTransaction.text = "$type1 최저가"
            type2 ->
                binding.recentTransaction.text = "$type2 최저가"
            type3 ->
                binding.recentTransaction.text = "$type3 최저가"
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}