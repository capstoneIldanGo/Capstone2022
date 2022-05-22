package com.ildango.capstone.resultdetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ildango.capstone.databinding.ActivitySearchDetailBinding
import com.ildango.capstone.productdetail.ProductDetailActivity
import com.ildango.capstone.data.repository.ProductRepository
import com.ildango.capstone.result.ResultActivity
import com.ildango.capstone.result.type1
import com.ildango.capstone.result.type2
import com.ildango.capstone.result.type3

class ResultDetailActivity : AppCompatActivity(){

    private var _binding: ActivitySearchDetailBinding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: ResultDetailViewModel
    private val repository = ProductRepository()
    private val viewModelFactory = ResultDetailViewModelFactory(repository)
    private lateinit var adapter: ProductListAdapter
    private lateinit var sortingSheet : SortingSheetFragment

    private var searchKeyword = ""
    private var page = 0
    private var type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultDetailViewModel::class.java)

        type = intent.getStringExtra("type").toString()
        sortingSheet = SortingSheetFragment(type)

        initView()
        onClickListener()
        setItemClickListener()

    }

    private fun initView() {
        setSearchView()
        setTextByType(intent.getStringExtra("type").toString())
        setRecyclerView()
        setScrollListener()
        setObserver()
    }

    private fun setRecyclerView() {
        binding.recyclerCourseItem.layoutManager = LinearLayoutManager(this)
        adapter = ProductListAdapter()
        binding.recyclerCourseItem.adapter = adapter
        viewModel.getData(page++)
    }

    private fun setObserver() {
        viewModel.product.observe(this, Observer {
            adapter.setItems(it)
        })
    }

    private fun setScrollListener() {
        binding.recyclerCourseItem.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPos = (binding.recyclerCourseItem.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = adapter!!.itemCount - 1

                if(!binding.recyclerCourseItem.canScrollVertically(1) && lastVisibleItemPos == itemTotalCount) {
                    viewModel.getData(page++)
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
        searchKeyword = intent.getStringExtra("keyword").toString()
        binding.searchView.setQuery(searchKeyword, false)
    }

    private fun onClickListener() {
        binding.btnSorting.setOnClickListener{
            sortingSheet.show(supportFragmentManager, SortingSheetFragment.TAG)
        }
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