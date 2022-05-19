package com.ildango.capstone.result

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ildango.capstone.databinding.ActivitySearchResultBinding
import com.ildango.capstone.resultdetail.ResultDetailActivity
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlinx.android.synthetic.main.fragment_one_week_chart_page.*

const val type1 = "내 주변"
const val type2 = "전국"
const val type3 = "미개봉/S급"

class ResultActivity : AppCompatActivity() {

    private var _binding: ActivitySearchResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ResultViewModel

    private val priceListTags = arrayListOf(type1, type2, type3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)

        vp_chart.adapter = ChartPagerAdapter(this)
        onClickListener()
        setPriceInfoList()
        setSearchView()
        setAlarmDialog()
    }

    private fun setAlarmDialog() {
        binding.btnMakeAlarm.setOnClickListener {
            AlarmDialog(intent.getStringExtra("keyword").toString()).show(supportFragmentManager, "AlarmDialog")
        }

    }

    private fun setPriceInfoList() {
        var priceListPrice = viewModel.getPricesByTag()
        val adapter = PriceListAdapter(priceListTags, priceListPrice)
        binding.listviewPriceList.adapter = adapter

        setListListener()
    }

    private fun setListListener() {
        binding.listviewPriceList.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val nextIntent = Intent(this, ResultDetailActivity::class.java)
                nextIntent.putExtra("keyword", intent.getStringExtra("keyword").toString())
                when (position) {
                    0 -> nextIntent.putExtra("type", type1)
                    1 -> nextIntent.putExtra("type", type2)
                    2 -> nextIntent.putExtra("type", type3)
                }
                startActivity(nextIntent)
            }
    }

    private fun setSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                finish()
                val intent = Intent(this@ResultActivity, ResultActivity::class.java)
                intent.putExtra("keyword", binding.searchView.query.toString())
                startActivity(intent)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                // 텍스트 값 바뀔 때
                return false
            }
        })

        binding.searchView.setQuery(intent.getStringExtra("keyword").toString(), false)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun onClickListener() {
        binding.btnOneMonth.setOnClickListener {
            vp_chart.setCurrentItem(0, true)
        }
        binding.btnOneWeek.setOnClickListener {
            vp_chart.setCurrentItem(1, true)
        }
    }

    private class ChartPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {
            return when(position){
                0->OneMonthChartFragment()
                else->OneWeekChartFragment()
            }
        }

        override fun getItemCount(): Int = 2
    }
}