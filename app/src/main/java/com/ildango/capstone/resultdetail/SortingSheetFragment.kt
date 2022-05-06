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
import kotlinx.android.synthetic.main.fragment_sorting_bottom_sheet.*


interface SortingSheetClickListener {
    fun onButtonClicked(id: Int)
}

class SortingSheetFragment() : BottomSheetDialogFragment() {

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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAllPlatform.setOnClickListener {
            if(btnAllPlatform?.isChecked == true) {
                btnJunggoMarket?.isChecked = true;
                btnCarrotMarket?.isChecked = true;
                btnThunderMarket?.isChecked = true;
            }
            else {
                btnJunggoMarket?.isChecked = false;
                btnCarrotMarket?.isChecked = false;
                btnThunderMarket?.isChecked = false;
            }
        }
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

    // 글자 클릭시 변화주기 추가

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkButtons() {
        if(btnCarrotMarket.isChecked==true && btnJunggoMarket.isChecked==true && btnThunderMarket.isChecked==true)
            btnAllPlatform.isChecked = true
        else
            btnAllPlatform.isChecked = false
    }

}
