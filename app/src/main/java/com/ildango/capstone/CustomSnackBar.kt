package com.ildango.capstone

import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.ildango.capstone.databinding.CustomSnackbarLayoutBinding

class CustomSnackBar(view: View, message: String) {

    companion object {
        fun make(view: View, message: String) = CustomSnackBar(view, message)
    }

    private var _binding: CustomSnackbarLayoutBinding? = null
    private val binding get() = _binding!!
    private val context = view.context
    private val snackBar = Snackbar.make(view, "", 5000)
    private val layout = snackBar.view as Snackbar.SnackbarLayout

    init {
        val inflater = LayoutInflater.from(context)
        _binding = CustomSnackbarLayoutBinding.inflate(inflater)

        with(layout) {
            removeAllViews()
            setPadding(30,5,20,5)
            setBackgroundColor(ContextCompat.getColor(context, R.color.logoColor))
            addView(binding.root, 0)
        }

        binding.tvSnackBarMessage.text = message
    }

    fun show() {
        snackBar.show()
    }
}
