package com.ildango.capstone.resultdetail

import androidx.lifecycle.*
import com.ildango.capstone.data.repository.ProductRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ResultDetailViewModel(private val productRepository: ProductRepository)
    : ViewModel() {

    private val productList = mutableListOf<ProductItem>()
    private val _product : MutableLiveData<List<ProductItem>> = MutableLiveData()
    val product: LiveData<List<ProductItem>> = _product

    fun getData(page:Int) {
        viewModelScope.launch {
            productRepository.getAllProduct(page)
                .onSuccess {
                    productList.addAll(it.productList)
                    _product.value = productList
                }
        }
    }

    fun getUrl(pos:Int): String {
        return product.value!!.get(pos).url
    }

    fun getId(pos: Int): Long {
        return product.value!!.get(pos).postId
    }
}

class ResultDetailViewModelFactory (private val repository: ProductRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ResultDetailViewModel(repository) as T
    }
}