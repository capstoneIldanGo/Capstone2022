package com.ildango.capstone.resultdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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