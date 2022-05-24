package com.ildango.capstone.resultdetail

import androidx.lifecycle.*
import com.ildango.capstone.data.repository.ProductRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ResultDetailViewModel(private val productRepository: ProductRepository)
    : ViewModel() {

    private var productList = mutableListOf<ProductItem>()
    private var _product : MutableLiveData<List<ProductItem>> = MutableLiveData()
    var product: LiveData<List<ProductItem>> = _product

    var productOrderType = MutableLiveData<String>()
    var productPlatform = MutableLiveData<List<Boolean>>()
    var productTag = MutableLiveData<List<Boolean>>()

    var isDismissed = MutableLiveData<Boolean>()

    fun resetData() {
        productList.clear()
        _product.value = productList
    }

    fun getData(keyword:String, page:Int) {
        viewModelScope.launch {
            productRepository.getAllProduct(keyword, productOrderType.value!!, page)
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
    fun setDismissed(value:Boolean) {
        isDismissed.value = value
    }
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