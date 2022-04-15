package com.example.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.example.capstone.databinding.ActivityMainBinding
import com.ildango.capstone.BottomSheetClickListener
import com.ildango.capstone.BottomSheetFragment


class MainActivity : AppCompatActivity(), BottomSheetClickListener {


    private var _binding: ActivityMainBinding?= null
    private val binding get() = _binding!!
    private val bottomSheet = BottomSheetFragment()



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

    override fun onButtonClicked(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}