package com.ildango.capstone.result

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import com.ildango.capstone.R
import com.ildango.capstone.databinding.DialogSetAlarmBinding

class AlarmDialog(context: Context) : Dialog(context) {

    private var _binding: DialogSetAlarmBinding ?= null
    private val binding  get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DialogSetAlarmBinding.inflate(LayoutInflater.from(context))

        setCanceledOnTouchOutside(false)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
    }

}
