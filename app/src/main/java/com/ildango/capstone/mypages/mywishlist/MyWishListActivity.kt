package com.ildango.capstone.mypages.mywishlist

import android.content.Intent
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.VectorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ildango.capstone.R
import com.ildango.capstone.data.repository.MyWishListRepository
import com.ildango.capstone.databinding.ActivityWishListBinding
import com.ildango.capstone.productdetail.ProductDetailActivity
import com.ildango.capstone.resultdetail.ProductViewHolder


class MyWishListActivity : AppCompatActivity() {

    private var _binding: ActivityWishListBinding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyWishListViewModel
    private val repository = MyWishListRepository()
    private val viewModelFactory = MyWishListViewModelFactory(repository)
    private lateinit var adapter: MyWishListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWishListBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyWishListViewModel::class.java)

        setRecyclerview()
        setObserver()
        setItemClickListener()
        setItemSwipe()
    }

    private fun setItemSwipe() {
        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeItem(viewHolder.absoluteAdapterPosition)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val height: Float = itemView.bottom.toFloat() - itemView.top.toFloat()
                val width: Float = height / 3

                val p = Paint()
                p.color = ContextCompat.getColor(this@MyWishListActivity, R.color.deleteButton)
                val bg = RectF(
                    itemView.left.toFloat() + width,
                    itemView.top.toFloat(),
                    itemView.right.toFloat(),
                    itemView.bottom.toFloat()
                )
                c.drawRoundRect(bg, 40F, 40F, p)

                val icon: Bitmap =
                    (getDrawable(R.drawable.ic_baseline_delete_24) as VectorDrawable).toBitmap()
                val iconDest = RectF(
                    itemView.right.toFloat() - 2 * width,
                    itemView.top.toFloat() + width,
                    itemView.right.toFloat() - width,
                    itemView.bottom.toFloat() - width
                )

                c.drawBitmap(icon, null, iconDest, p)

                super.onChildDraw(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerviewWishList)
    }

    private fun setRecyclerview() {
        binding.recyclerviewWishList.layoutManager = LinearLayoutManager(this)
        adapter = MyWishListAdapter()
        binding.recyclerviewWishList.adapter = adapter
        viewModel.getData()
    }

    private fun setObserver() {
        viewModel.items.observe(this, Observer {
            adapter.setItems(it)
        })
    }

    private fun setItemClickListener() {
        adapter.setItemClickListener(object : ProductViewHolder.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                Intent(this@MyWishListActivity, ProductDetailActivity::class.java).apply {
                    putExtra("keyword", "")
                    putExtra("postid", viewModel.getId(position))
                    putExtra("url", viewModel.getUrl(position))
                }.run { startActivity(this) }
            }
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
