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
    private lateinit var viewModel: GetInfoViewModel
    private val repository = LocationRepository()
    private val viewModelFactory = GetInfoViewModelFactory(repository)
    private lateinit var pref:SharedPreferences

    private lateinit var cityAdapter: ArrayAdapter<String>
    private lateinit var stateAdapter: ArrayAdapter<String>
    private var isFirstRun = false
    private var isCreated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityGetInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GetInfoViewModel::class.java)

        cityAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item)
        stateAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item)
        pref = getSharedPreferences("Information", MODE_PRIVATE)

        isFirstRun = intent.getBooleanExtra("isFirst", false)

        changeSettingIfItsFirst()
        setSpinner()
        setOnBtnClick()
    }

    private fun changeSettingIfItsFirst() {
        if(isFirstRun) {
            binding.btnCancelInfo.visibility = View.GONE
        } else {
            binding.edittextName.setText(pref.getString("name", ""))
        }
    }

    private fun setSpinner() {
        viewModel.cityList.observe(this) {
            cityAdapter.addAll(it)
            binding.spinnerCity.adapter = cityAdapter
            if(!isFirstRun) {
                binding.spinnerCity.setSelection(pref.getInt("city", 0))
            }
        }

        viewModel.stateList.observe(this) {
            stateAdapter.clear()
            stateAdapter.addAll(it)
            binding.spinnerState.adapter = stateAdapter
            if(!isFirstRun && !isCreated) {
                binding.spinnerState.setSelection(pref.getInt("state", 0))
                isCreated = true
            }
        }

        binding.spinnerCity.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.getStates(viewModel.cityList.value!![p2])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    private fun setOnBtnClick() {
       binding.btnSubmitInfo.setOnClickListener {
            if(binding.edittextName.text.isNotEmpty()) {
                saveName(binding.edittextName.text.toString())
                saveLocation(binding.spinnerCity.selectedItemPosition, binding.spinnerState.selectedItemPosition)
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

    private fun saveLocation(city:Int, state:Int) {
        val prefEditor: SharedPreferences.Editor = pref.edit()
        prefEditor.putInt("city", city)
        prefEditor.putString("cityInfo", viewModel.cityList.value!![city])
        prefEditor.putInt("state", state)
        prefEditor.putString("stateInfo", viewModel.stateList.value!![state])
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