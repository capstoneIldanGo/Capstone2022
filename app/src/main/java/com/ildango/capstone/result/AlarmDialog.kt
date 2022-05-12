package com.ildango.capstone.result

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.ildango.capstone.data.repository.MyAlarmListRepository
import com.ildango.capstone.databinding.DialogSetAlarmBinding
import com.ildango.capstone.mypages.myalarmlist.MyAlarmItem
import com.ildango.capstone.mypages.myalarmlist.MyAlarmListViewModel
import com.ildango.capstone.mypages.myalarmlist.MyAlarmListViewModelFactory

class AlarmDialog(val keyword:String) : DialogFragment(), View.OnClickListener {

    private var _binding: DialogSetAlarmBinding ?= null
    private val binding  get() = _binding!!
    private val repository = MyAlarmListRepository()
    private val viewModelFactory = MyAlarmListViewModelFactory(repository)
    private var viewModel: MyAlarmListViewModel ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyAlarmListViewModel::class.java)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogSetAlarmBinding.inflate(inflater, container, false)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCancelMakingAlarm.setOnClickListener(this)
        binding.btnSubmitAlarm.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0) {
            binding.btnCancelMakingAlarm-> {
                dismiss()
            }
            binding.btnSubmitAlarm-> {
                val price = Integer.parseInt(binding.editTextGetPrice.text.toString())
                dismiss()
            }
        }
    }
}
