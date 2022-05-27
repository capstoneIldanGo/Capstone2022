package com.ildango.capstone.result

import androidx.lifecycle.*
import com.ildango.capstone.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ResultViewModel(private val productRepository: ProductRepository) : ViewModel()  {

    var lowestPriceAroundArea = MutableLiveData<Int>()
    var lowestPriceOfAll = MutableLiveData<Int>()
    var lowestPriceOfSClass = MutableLiveData<Int>()

    private val twoWeeksChartData = arrayOf(700,300,200,1200,500,200,500)
    private val oneWeekChartData = arrayOf(700,300,200,1200,500,200,500)

    // Chart 관련
    fun getTwoWeeksChartData() : Array<Int> { return twoWeeksChartData }
    fun getOneWeekChartData() : Array<Int> { return oneWeekChartData }

    // 태그별 최저가
    fun getLowestAroundMyArea() {
        lowestPriceAroundArea.value = 15000
    }

    fun getLowestOfAll(keyword:String) {
        viewModelScope.launch {
            productRepository.getProductPrice(keyword)
                .onSuccess {
                    lowestPriceOfAll.value = it.price
                }
                .onFailure {
                    lowestPriceOfAll.value = 0
                }
        }
    }

    fun getLowestOfSClass(keyword:String) {
        viewModelScope.launch {
            productRepository.getProductPrice(keyword, mint=true)
                .onSuccess {
                    lowestPriceOfSClass.value = it.price
                }
                .onFailure {
                    lowestPriceOfSClass.value = 0
                }
        }
    }

}

class ResultViewModelFactory (private val repository: ProductRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ResultViewModel(repository) as T
    }
}