package com.ildango.capstone.resultdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultDetailViewModelFactory (private val repository: ProductRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ResultDetailViewModel(repository) as T
    }
}