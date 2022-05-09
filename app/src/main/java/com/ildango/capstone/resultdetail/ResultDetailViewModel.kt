package com.ildango.capstone.resultdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ildango.capstone.service.ProductItem
import kotlinx.coroutines.launch
import retrofit2.Response

class ResultDetailViewModel(private val productRepository: ProductRepository)
    : ViewModel() {

    val product : MutableLiveData<Response<ProductItem>> = MutableLiveData()
    // val product: LiveData<ProductItem> = _product

    fun getData() {
        viewModelScope.launch {
            val response = productRepository.getAllProduct()
            product.value = response
        }
    }
}

class ResultDetailViewModelFactory (private val repository: ProductRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ResultDetailViewModel(repository) as T
    }
}