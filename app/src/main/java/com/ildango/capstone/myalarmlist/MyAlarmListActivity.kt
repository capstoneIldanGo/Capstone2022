package com.ildango.capstone.myalarmlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ildango.capstone.databinding.ActivityPriceAlarmBinding
import com.ildango.capstone.mywishlist.MyWishItem
import com.ildango.capstone.mywishlist.MyWishListAdapter

class MyAlarmListActivity : AppCompatActivity() {

    private var _binding: ActivityPriceAlarmBinding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyAlarmListViewModel

    private val mItems = MutableLiveData<ArrayList<MyAlarmItem>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPriceAlarmBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MyAlarmListViewModel::class.java)

        // list view
        binding.recyclerviewAlarmList.layoutManager = LinearLayoutManager(this)

        val dataObserver: Observer<ArrayList<MyAlarmItem>> =
            Observer { livedata ->
                mItems.value = livedata
                val mAdapter = MyAlarmListAdapter(mItems)
                binding.recyclerviewAlarmList.adapter = mAdapter
            }
        viewModel.liveData.observe(this, dataObserver)

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}