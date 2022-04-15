package com.example.capstone

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.capstone.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.lang.Exception

interface BottomSheetClickListener {
    fun onButtonClicked(id: Int)
}

class BottomSheetFragment() : BottomSheetDialogFragment() {

    private var _binding: BottomSheetBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomSheetClickListener: BottomSheetClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val TAG = "BottomSheet"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            bottomSheetClickListener = context as BottomSheetClickListener
        } catch (e: Exception) {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFavorite.setOnClickListener {
            bottomSheetClickListener.onButtonClicked(R.id.btn_favorite)
            dismiss()
        }
        binding.btnAlarm.setOnClickListener {
            bottomSheetClickListener.onButtonClicked(R.id.btn_alarm)
            dismiss()
        }
        binding.btnLogstate.setOnClickListener {
            bottomSheetClickListener.onButtonClicked(R.id.btn_logstate)
            dismiss()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

