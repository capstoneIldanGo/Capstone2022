package com.ildango.capstone.resultdetail

import android.app.Activity
import android.app.Dialog
import com.ildango.capstone.BottomSheetClickListener
import com.ildango.capstone.R
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ildango.capstone.databinding.FragmentSortingBottomSheetBinding
import com.ildango.capstone.result.type1
import com.ildango.capstone.result.type2
import com.ildango.capstone.result.type3


interface SortingSheetClickListener {
    fun onButtonClicked(id: Int)
}

class SortingSheetFragment(private val type:String) : BottomSheetDialogFragment() {

    private var _binding: FragmentSortingBottomSheetBinding? = null
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
        _binding = FragmentSortingBottomSheetBinding.inflate(inflater, container, false)
        initButtonState()
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupRatio(bottomSheetDialog)
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPlatformButton()

        binding.btnCarrotMarket.setOnClickListener{
            checkButtons()
        }
        binding.btnJunggoMarket.setOnClickListener{
            checkButtons()
        }
        binding.btnThunderMarket.setOnClickListener{
            checkButtons()
        }
    }

    private fun initButtonState() {
        binding.btnSortingLatest.isChecked = true
        binding.btnAllPlatform.isChecked = true
        when (type) {
            type1->{
                binding.btnTagFavoriteArea.isChecked = true
            }
            type2-> {

            }
            type3-> {
                binding.btnTagSclass.isChecked = true
            }
        }
    }

    private fun setPlatformButton() {
        binding.btnAllPlatform.setOnClickListener {
            if(binding.btnAllPlatform.isChecked) {
                binding.btnJunggoMarket.isChecked = true;
                binding.btnCarrotMarket.isChecked = true;
                binding.btnThunderMarket.isChecked = true;
            }
            else {
                binding.btnJunggoMarket.isChecked = false;
                binding.btnCarrotMarket.isChecked = false;
                binding.btnThunderMarket.isChecked = false;
            }
        }
    }

    private fun checkButtons() {
        binding.btnAllPlatform.isChecked = binding.btnCarrotMarket.isChecked && binding.btnJunggoMarket.isChecked && binding.btnThunderMarket.isChecked
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as View
        val behavior = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet!!.layoutParams
        layoutParams.height = getBottomSheetDialogDefaultHeight()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 35 / 100
    }

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    companion object {
        const val TAG = "SortingSheet"
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
