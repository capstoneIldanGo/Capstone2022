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
    private lateinit var adapter: MyAlarmListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPriceAlarmListBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyAlarmListViewModel::class.java)

        setRecyclerview()
        setObserver()
    }

    private fun setRecyclerview() {
        binding.recyclerviewAlarmList.layoutManager = LinearLayoutManager(this)
        adapter = MyAlarmListAdapter()
        binding.recyclerviewAlarmList.adapter = adapter
        viewModel.getData()
    }

    private fun setObserver() {
        viewModel.items.observe(this, Observer {
            adapter.setItems(it)
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}