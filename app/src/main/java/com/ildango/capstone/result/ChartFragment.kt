package com.ildango.capstone.result

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.ildango.capstone.R
import com.ildango.capstone.databinding.FragmentChartPageBinding

class ChartFragment(private val priceList: Array<Int>): Fragment() {
    private var _binding: FragmentChartPageBinding? = null
    private val binding get() = _binding!!

    lateinit var lineData: LineData
    lateinit var lineList: ArrayList<Entry>
    lateinit var lineDataSet: LineDataSet

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChartPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeChart(7)
    }

    private fun makeChart(k : Int) {
        binding.priceChart.setNoDataText("데이터가 없습니다.")

        setChart()

        lineList = ArrayList()
        // db에서 값 가져와 값 집어 넣기 : ArrayList<Int>()
        // val priceList = arrayOf(700,300,200,1200,500,200,500)

        for(i in priceList.indices){
            lineList.add(Entry(i.toFloat(), priceList[i].toFloat()))
        }

        lineDataSet = LineDataSet(lineList, null)
        lineData = LineData(lineDataSet)
        lineData.setValueTextSize(0f)
        binding.priceChart.data = lineData
        binding.priceChart.invalidate()
    }

    private fun setChart() {
        val mymarker = MyMarkerView(context, R.layout.fragment_chart_page)

        binding.priceChart.legend.isEnabled = false
        binding.priceChart.apply() {
            setExtraOffsets(2f, 2f, 2f, 2f)
            axisLeft.setDrawLabels(true)
            axisRight.setDrawLabels(false)
            description.isEnabled = false
            isDoubleTapToZoomEnabled = false
            isScaleYEnabled = false
            isScaleXEnabled = false
            xAxis.isEnabled = false
            marker = mymarker
        }
    }

    inner class MyMarkerView(context: Context?, layoutResource: Int) : MarkerView(context, layoutResource){
        private lateinit var textView: TextView
        var price = ""

        override fun refreshContent(e: Entry?, highlight: Highlight?) {
            textView = binding.tvChartValue
            price = e?.y.toString()
            textView.text = "${price} 원"
            super.refreshContent(e, highlight)
        }

        override fun getOffset(): MPPointF {
            return MPPointF(-(width / 2f), -height.toFloat())
        }
    }
}