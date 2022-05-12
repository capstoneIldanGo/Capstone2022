package com.ildango.capstone.mypages.myalarmlist

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ildango.capstone.databinding.ActivityPriceAlarmListBinding
import com.ildango.capstone.data.repository.MyAlarmListRepository

class MyAlarmListActivity : AppCompatActivity() {

    private var _binding: ActivityPriceAlarmListBinding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyAlarmListViewModel
    private val repository = MyAlarmListRepository()
    private val viewModelFactory = MyAlarmListViewModelFactory(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPriceAlarmListBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyAlarmListViewModel::class.java)

        // list view
        binding.recyclerviewAlarmList.layoutManager = LinearLayoutManager(this)

        viewModel.getData()
        setObserver()
    }

    private fun setObserver() {
        viewModel.items.observe(this, Observer {
            if(it.isSuccessful) {
                val mAdapter = MyAlarmListAdapter(viewModel.items)
                binding.recyclerviewAlarmList.adapter = mAdapter
            }
            else {
                Log.d("Response", "ERROR:${it.errorBody().toString()}")
            }
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}