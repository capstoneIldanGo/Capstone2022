package com.ildango.capstone.resultdetail

import androidx.lifecycle.*
import com.ildango.capstone.data.model.ProductItem
import com.ildango.capstone.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ResultDetailViewModel(private val productRepository: ProductRepository)
    : ViewModel() {

    private var productList = mutableListOf<ProductItem>()
    private var _product : MutableLiveData<List<ProductItem>> = MutableLiveData()
    var product: LiveData<List<ProductItem>> = _product

    var productOrderType = MutableLiveData<String>()
    var productPlatform = MutableLiveData<List<Boolean>>()
    var productTag = MutableLiveData<List<Boolean>>()

    private var infoCity = MutableLiveData<String>()
    private var infoState = MutableLiveData<String>()

    var isDismissed = MutableLiveData<Boolean>()

    fun resetData() {
        productList.clear()
        _product.value = productList
    }

    fun getData(keyword:String, page:Int) {
        viewModelScope.launch {
            productRepository.getAllProduct(keyword = keyword, order = productOrderType.value!!, page = page,
                platform = productPlatform.value!!, tag = productTag.value!!, city = infoCity.value!!, state = infoState.value!!)
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

    // location info
    fun setCity(city:String) {infoCity.value = city}
    fun setState(state:String) {infoState.value = state}
}

class ResultDetailViewModelFactory (private val repository: ProductRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ResultDetailViewModel(repository) as T
    }
}