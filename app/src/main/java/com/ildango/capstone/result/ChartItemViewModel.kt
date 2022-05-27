package com.ildango.capstone.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ildango.capstone.data.repository.ChartRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ChartItemViewModel(private val chartRepository: ChartRepository) : ViewModel() {
    val items: MutableLiveData<Response<List<ChartItem>>> = MutableLiveData()

    private val twoWeeksChartData = arrayOf(700,300,200,1200,500,200,500)
    private val oneWeekChartData = arrayOf(700,300,200,1200,500,200,500)

    // Chart 관련
    fun getTwoWeeksChartData() : Array<Int> { return twoWeeksChartData }
    fun getOneWeekChartData() : Array<Int> { return oneWeekChartData }


    fun getData(keyword:String) {
        viewModelScope.launch {
            val response = chartRepository.getChartPrice(keyword)
            items.value = response
        }
    }

    fun getValue(pos :Int) : Int{
        return items.value!!.body()!!.get(pos).avgPrice
    }

}

class ChartItemViewModelFactory (private val repository: ChartRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChartItemViewModel(repository) as T
    }
}

