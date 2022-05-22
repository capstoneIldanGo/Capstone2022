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
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ildango.capstone.data.repository.ProductRepository
import com.ildango.capstone.databinding.FragmentSortingBottomSheetBinding


interface SortingSheetClickListener {
    fun onButtonClicked(id: Int)
}

class SortingSheetFragment: BottomSheetDialogFragment() {

    private var _binding: FragmentSortingBottomSheetBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ResultDetailViewModel
    private val repository = ProductRepository()
    private val viewModelFactory = ResultDetailViewModelFactory(repository)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(ResultDetailViewModel::class.java)
        initButtonState()

        setPlatformButton()
    }

    // radio, check buttons
    private fun initButtonState() {
        if(viewModel.productOrderType.value == orderByDate)
            binding.btnSortingLatest.isChecked = true
        else if(viewModel.productOrderType.value == orderByPrice)
            binding.btnSortingLowest.isChecked = true

        if(viewModel.productPlatform.value!![0])
            binding.btnCarrotMarket.isChecked = true
        if(viewModel.productPlatform.value!![1])
            binding.btnJunggoMarket.isChecked = true
        if(viewModel.productPlatform.value!![2])
            binding.btnThunderMarket.isChecked = true
        checkAllPlatformButton()

        if(viewModel.productTag.value!![0])
            binding.btnTagFavoriteArea.isChecked = true
        if(viewModel.productTag.value!![1])
            binding.btnTagSclass.isChecked = true
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
        binding.btnCarrotMarket.setOnClickListener{
            checkAllPlatformButton()
        }
        binding.btnJunggoMarket.setOnClickListener{
            checkAllPlatformButton()
        }
        binding.btnThunderMarket.setOnClickListener{
            checkAllPlatformButton()
        }
    }

    private fun checkAllPlatformButton() {
        binding.btnAllPlatform.isChecked = binding.btnCarrotMarket.isChecked && binding.btnJunggoMarket.isChecked && binding.btnThunderMarket.isChecked
    }


    // bottom sheet
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
