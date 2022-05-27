package com.ildango.capstone.result

import android.util.Log
import androidx.lifecycle.*
import com.ildango.capstone.data.model.MyWishItem
import com.ildango.capstone.data.repository.ChartRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ChartItemViewModel(private val chartRepository: ChartRepository) : ViewModel() {

    private var itemList = mutableListOf<ChartItem>()
    private var _items : MutableLiveData<List<ChartItem>> = MutableLiveData()
    val items: LiveData<List<ChartItem>> = _items


    var chartPriceList = MutableLiveData<List<Int>>()

    private val twoWeeksChartData = arrayOf(700,300,200,1200,500,200,500)
    private val oneWeekChartData = arrayOf(700,300,200,1200,500,200,500)

    // Chart 관련
    fun getTwoWeeksChartData() : Array<Int> { return twoWeeksChartData }
    fun getOneWeekChartData() : Array<Int> { return oneWeekChartData }

    fun getData(keyword:String) {
        viewModelScope.launch {
            chartRepository.getChartPrice(keyword)
                    .onSuccess {
                        itemList.addAll(it)
                        _items.value = itemList
                    }
        }
    }

    fun getValue(pos :Int) : Int{
        return items.value!!.get(pos).avgPrice
    }

    companion object {
        const val TAG = "chartGood"
    }

}

class ChartItemViewModelFactory (private val repository: ChartRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChartItemViewModel(repository) as T
    }
}

