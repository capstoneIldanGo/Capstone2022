package com.ildango.capstone.result

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.ildango.capstone.databinding.DialogSetAlarmBinding

class AlarmDialog(context: Context) : Dialog(context), View.OnClickListener {

    private var _binding: DialogSetAlarmBinding ?= null
    private val binding  get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DialogSetAlarmBinding.inflate(LayoutInflater.from(context))

        setCanceledOnTouchOutside(false)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnCancelMakingAlarm.setOnClickListener(this)
        binding.btnSubmitAlarm.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0) {
            binding.btnCancelMakingAlarm-> {
                dismiss()
            }
            binding.btnSubmitAlarm-> {
                val price = binding.editTextGetPrice.text
                // price 값 전달 필요
                dismiss()
            }
        }
    }
}
