package com.ildango.capstone.myalarmlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.ildango.capstone.databinding.ActivityPriceAlarmListBinding
import com.ildango.capstone.data.repository.MyAlarmListRepository
import com.ildango.capstone.SwipeItemCallback

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
        setItemSwipe()
    }

    private fun setRecyclerview() {
        binding.recyclerviewAlarmList.layoutManager = LinearLayoutManager(this)
        adapter = MyAlarmListAdapter()
        binding.recyclerviewAlarmList.adapter = adapter
        viewModel.getData()
    }

    private fun setItemSwipe() {
        val swipeItemCallback = SwipeItemCallback(this, adapter).apply {
            setClamp(resources.displayMetrics.widthPixels.toFloat() / 5)
        }
        ItemTouchHelper(swipeItemCallback).attachToRecyclerView(binding.recyclerviewAlarmList)
        binding.recyclerviewAlarmList.setOnTouchListener { _, motionEvent ->
            val removedPos = swipeItemCallback.onDeleteIcon(motionEvent)
            if(removedPos != -1) viewModel.deleteItem(1, removedPos)
            swipeItemCallback.removePreviousClamp(binding.recyclerviewAlarmList)
            false
        }
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