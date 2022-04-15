package com.ildango.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import com.ildango.capstone.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), BottomSheetClickListener {


    private var _binding: ActivityMainBinding ?= null
    private val binding get() = _binding!!
    private val bottomSheet = BottomSheetFragment()
    private val searchResult = SearchResultFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      //  val navController = Navigation.findNavController(binding.navHostActivityMain)


        binding.btnSearch.setOnClickListener {
            val nextIntent = Intent(this, ResultActivity::class.java)
            startActivity(nextIntent)
            //   navController.navigate(R.id.action_mainActivity_to_searchResultFragment)
        }

        binding.viewUnderSearchBar.setOnTouchListener(object:OnSwipeTouchListener(this@MainActivity) {
            override fun onSwipeTop() {
                bottomSheet.show(supportFragmentManager, BottomSheetFragment.TAG)
            }
        })

    }

    override fun onButtonClicked(id: Int) {
        when(id) {
            R.id.btn_favorite -> {
                val intent = Intent(this, MyWishList::class.java)
                startActivity(intent)
            }
            R.id.btn_alarm -> {

            }
            R.id.btn_logstate -> {

            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}