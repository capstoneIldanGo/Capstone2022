package com.ildango.capstone.resultdetail

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.marginStart
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ildango.capstone.R
import com.ildango.capstone.databinding.ActivitySearchDetailBinding
import com.ildango.capstone.productdetail.ProductDetailActivity
import com.ildango.capstone.data.repository.ProductRepository
import com.ildango.capstone.result.ResultActivity
import com.ildango.capstone.result.type1
import com.ildango.capstone.result.type2
import com.ildango.capstone.result.type3

const val orderByDate = "UPLOADDATE_DESC"
const val orderByPrice = "PRICE_ASC"

class ResultDetailActivity : AppCompatActivity(){

    private var _binding: ActivitySearchDetailBinding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: ResultDetailViewModel
    private val repository = ProductRepository()
    private val viewModelFactory = ResultDetailViewModelFactory(repository)
    private lateinit var adapter: ProductListAdapter
    private lateinit var sortingSheet : SortingSheetFragment

    private var searchKeyword = ""
    private var type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultDetailViewModel::class.java)

        type = intent.getStringExtra("type").toString()
        sortingSheet = SortingSheetFragment()
        searchKeyword = intent.getStringExtra("keyword").toString()

        initSortingFilter()
        initView()
        onClickListener()
        setItemClickListener()

        observeSortingFilterChanging()
    }

    private fun initView() {
        setSearchView()
        getArea()
        setTextByType(intent.getStringExtra("type").toString())
        setRecyclerView()
        setScrollListener()
        setObserver()
    }

    private fun initSortingFilter() {
        viewModel.setOrderType(orderByPrice)
        viewModel.setPlatform(listOf(true, true, true))
        when(type) {
            type1-> viewModel.setTag(listOf(true, false))
            type2-> viewModel.setTag(listOf(false, false))
            type3-> viewModel.setTag(listOf(false, true))
        }
    }

    private fun observeSortingFilterChanging() {
        viewModel.isDismissed.observe(this, Observer{
            if(it) {
                viewModel.resetData()
                setRecyclerView()
                binding.recyclerCourseItem.clearOnScrollListeners()
                setScrollListener()
                setItemClickListener()
            }
        })
    }

    private fun setRecyclerView() {
        binding.recyclerCourseItem.layoutManager = LinearLayoutManager(this)
        adapter = ProductListAdapter()
        binding.recyclerCourseItem.adapter = adapter
        viewModel.getData(searchKeyword, 0)
    }

    private fun setObserver() {
        viewModel.product.observe(this, Observer {
            adapter.setItems(it)

            if(it.isEmpty()) {
                setNullImage()
            }
        })
    }

    private fun setNullImage() {
        val nullImageView = ImageView(this)
        nullImageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val density = resources.displayMetrics.density
        nullImageView.setPadding((density*40).toInt(),0,(density*40).toInt(),0)
        nullImageView.setImageResource(R.drawable.logo_crying)

        binding.root.addView(nullImageView)
    }

    private fun setScrollListener() {
        var page = 1
        binding.recyclerCourseItem.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPos = (binding.recyclerCourseItem.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = adapter.itemCount - 1

                if(!binding.recyclerCourseItem.canScrollVertically(1) && lastVisibleItemPos == itemTotalCount) {
                    viewModel.getData(searchKeyword, page++)
                }
            }
        })
    }

    private fun setItemClickListener() {
        adapter.setItemClickListener(object : ProductViewHolder.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                Intent(this@ResultDetailActivity, ProductDetailActivity::class.java).apply {
                    putExtra("keyword", searchKeyword)
                    putExtra("postid", viewModel.getId(position))
                    putExtra("url", viewModel.getUrl(position))
                }.run { startActivity(this) }
            }
        })
    }

    private fun setSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                finish()
                var intent = Intent(this@ResultDetailActivity, ResultActivity::class.java)
                intent.putExtra("keyword", binding.searchView.query.toString())
                startActivity(intent)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                // 텍스트 값 바뀔 때
                return false
            }
        })
        binding.searchView.setQuery(searchKeyword, false)
    }

    private fun onClickListener() {
        binding.btnSorting.setOnClickListener{
            sortingSheet.show(supportFragmentManager, SortingSheetFragment.TAG)
        }
    }

    private fun getArea() {
        val pref: SharedPreferences = getSharedPreferences("Information", MODE_PRIVATE)
        viewModel.setCity(pref.getString("cityInfo", "")!!)
        viewModel.setState(pref.getString("stateInfo", "")!!)
    }

    private fun setTextByType(type:String) {
        when(type){
            type1 ->
                binding.tvRecentTransaction.text = "$type1 최저가"
            type2 ->
                binding.tvRecentTransaction.text = "$type2 최저가"
            type3 ->
                binding.tvRecentTransaction.text = "$type3 최저가"
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}