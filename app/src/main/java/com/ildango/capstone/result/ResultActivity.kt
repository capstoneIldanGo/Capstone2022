package com.ildango.capstone.result

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.ildango.capstone.databinding.ActivitySearchResultBinding
import com.ildango.capstone.resultdetail.ResultDetailActivity
import kotlinx.android.synthetic.main.activity_search_result.*

const val type1 = "내 주변"
const val type2 = "전국"
const val type3 = "미개봉/S급"

class ResultActivity : AppCompatActivity() {

    private var _binding: ActivitySearchResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ResultViewModel

    private val priceListTags = arrayListOf(type1, type2, type3)

    lateinit var lineData: LineData
    lateinit var lineList: ArrayList<Entry>
    lateinit var lineDataSet: LineDataSet


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)

        val intent = intent
        val searchKeyword = intent.getStringExtra("keyword").toString()
        binding.searchView.setQuery(searchKeyword, false)

        // listview
        var priceListPrice = viewModel.getPricesByTag()
        val adapter = PriceListAdapter(priceListTags, priceListPrice)
        binding.listviewPriceList.adapter = adapter

        // chart
        makeChart()

        binding.listviewPriceList.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val nextIntent = Intent(this, ResultDetailActivity::class.java)
                nextIntent.putExtra("keyword", searchKeyword)
                when (position) {
                    0 -> nextIntent.putExtra("type", type1)
                    1 -> nextIntent.putExtra("type", type2)
                    2 -> nextIntent.putExtra("type", type3)
                }
                startActivity(nextIntent)
            }

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
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun makeChart() {
        lineChart.setNoDataText("데이터가 없습니다.")

        setChart()

        // 데이터 삽입 y값에..
        lineList = ArrayList()
        lineList.add(Entry(0f, 100f))
        lineList.add(Entry(1f, 300f))
        lineList.add(Entry(2f, 200f))
        lineList.add(Entry(3f, 1200f))
        lineList.add(Entry(4f, 500f))

        lineDataSet = LineDataSet(lineList, null)
        lineData = LineData(lineDataSet)
        lineData.setValueTextSize(10f)
        lineChart.data = lineData
        lineChart.invalidate()
    }

    private fun setChart() {
        val xAxis = lineChart.xAxis
        val xAxisValues = arrayOf<String>("세달 전", "한달 전", "이주일 전", "일주일 전", "어제")

        xAxis.apply {
            isEnabled = true
            position = XAxis.XAxisPosition.BOTTOM
            granularity = 1f
            valueFormatter = IndexAxisValueFormatter(xAxisValues)
            textSize = 12f
        }

        lineChart.apply() {
            setVisibleXRangeMaximum(4f)
            setExtraOffsets(2f, 2f, 2f, 2f)
            axisLeft.setDrawLabels(false)
            axisRight.setDrawLabels(false)
            description.isEnabled = false
            isDoubleTapToZoomEnabled = false
            isScaleYEnabled = false
            isScaleXEnabled = false
        }
    }

}