package com.ildango.capstone.myinfo

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ildango.capstone.R
import com.ildango.capstone.data.repository.LocationRepository
import com.ildango.capstone.databinding.ActivityGetInfoBinding

class GetInfoActivity : AppCompatActivity() {

    private var _binding: ActivityGetInfoBinding?= null
    private val binding get() = _binding!!
    private var isFirstRun = false
    private lateinit var viewModel: GetInfoViewModel
    private val repository = LocationRepository()
    private val viewModelFactory = GetInfoViewModelFactory(repository)
    private lateinit var pref:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityGetInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GetInfoViewModel::class.java)
        pref = getSharedPreferences("Information", MODE_PRIVATE)

        isFirstRun = intent.getBooleanExtra("isFirst", false)

        changeBtnIfItsFirst()
        setSpinner()
        setOnBtnClick()
    }

    private fun changeBtnIfItsFirst() {
        if(isFirstRun) {
            binding.btnCancelInfo.visibility = View.GONE
        }
    }

    private fun setSpinner() {
        val cityAdapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item)
        val stateAdapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item)

        viewModel.cityList.observe(this) {
            cityAdapter.addAll(it)
            binding.spinnerCity.adapter = cityAdapter
        }

        viewModel.stateList.observe(this) {
            stateAdapter.clear()
            stateAdapter.addAll(it)
            binding.spinnerState.adapter = stateAdapter
        }

        binding.spinnerCity.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.getStates(viewModel.cityList.value!![p2])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO()
            }
        }
    }

    private fun setOnBtnClick() {
       binding.btnSubmitInfo.setOnClickListener {
            if(binding.edittextName.text.isNotEmpty()) {
                saveName(binding.edittextName.text.toString())
                saveLocation(binding.spinnerCity.selectedItem.toString(), binding.spinnerState.selectedItem.toString())
                finish()
            } else {
                Toast.makeText(this, "모든 정보를 입력해주세용!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnCancelInfo.setOnClickListener {
            finish()
        }
    }

    private fun saveName(name:String) {
        val prefEditor: SharedPreferences.Editor = pref.edit()
        prefEditor.putString("name", name)
        prefEditor.apply()
    }

    private fun saveLocation(city:String, state:String) {
        val prefEditor: SharedPreferences.Editor = pref.edit()
        prefEditor.putString("city", city)
        prefEditor.putString("state", state)
        prefEditor.apply()
    }

    override fun onBackPressed() {
        if(!isFirstRun)
            super.onBackPressed()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}