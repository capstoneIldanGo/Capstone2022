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

    private var _binding: ActivitySearchResultBinding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel : ResultViewModel

    private val priceListTags = arrayListOf(type1, type2, type3)

    lateinit var lineData : LineData
    lateinit var lineList : ArrayList<Entry>
    lateinit var lineDataSet : LineDataSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)

        setPriceInfoList()
        setSearchView()
        setAlarmDialog()

        // chart
        makeChart()
    }

    private fun setAlarmDialog() {
        binding.btnMakeAlarm.setOnClickListener {
            // dialog 생성
            AlarmDialog(context = this).show()
       }
    }

    private fun setPriceInfoList() {
        var priceListPrice = viewModel.getPricesByTag()
        val adapter = PriceListAdapter(priceListTags, priceListPrice)
        binding.listviewPriceList.adapter = adapter

        setListListener()
    }

    private fun setListListener() {
        binding.listviewPriceList.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val nextIntent = Intent(this, ResultDetailActivity::class.java)
            nextIntent.putExtra("keyword", intent.getStringExtra("keyword").toString())
            when(position) {
                0->nextIntent.putExtra("type", type1)
                1->nextIntent.putExtra("type", type2)
                2->nextIntent.putExtra("type", type3)
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

    private fun makeChart(){
        lineChart.setNoDataText("데이터가 없습니다.")

        setChart()

        lineList = ArrayList()
        lineList.add(Entry(10f, 100f))
        lineList.add(Entry(20f, 300f))
        lineList.add(Entry(30f, 200f))
        lineList.add(Entry(40f, 600f))
        lineList.add(Entry(50f, 500f))

        lineDataSet = LineDataSet(lineList, "count")
        lineData = LineData(lineDataSet)
        lineChart.data=lineData
        lineChart.invalidate()
    }

    private fun setChart() {
        val xAxis = lineChart.xAxis
        val xAxisVals = arrayOf<String>("세달 전", "한달 전", "이주일 전", "일주일 전", "어제")

        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            granularity = 1f
            valueFormatter = IndexAxisValueFormatter(xAxisVals)
        }


        lineChart.apply() {
            setVisibleXRangeMaximum(4f)
            setPinchZoom(false)
            setExtraOffsets(2f, 2f, 2f, 2f)
        }
    }

    }