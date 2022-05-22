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

    val productOrderType = MutableLiveData<String>()
    val productPlatform = MutableLiveData<List<Boolean>>()
    val productTag = MutableLiveData<List<Boolean>>()


    fun getData(order:String, page:Int) {
        viewModelScope.launch {
            productRepository.getAllProduct(order, page)
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

    // sorting
    fun setOrderType(value:String) {
        productOrderType.value = value
    }
    fun setPlatform(value:List<Boolean>) {
        productPlatform.value = value
    }
    fun setTag(value:List<Boolean>) {
        productTag.value = value
    }
}

class ResultDetailViewModelFactory (private val repository: ProductRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ResultDetailViewModel(repository) as T
    }
}