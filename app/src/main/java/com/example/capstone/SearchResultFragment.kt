package com.example.capstone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.databinding.FragmentSearchResultBinding
// import androidx.navigation.fragment.findNavController

class SearchResultFragment : Fragment(){

    private lateinit var searchResultViewModel: SearchResultViewModel
    private var _binding : FragmentSearchResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
        ) : View? {
            searchResultViewModel = ViewModelProvider(this).get(SearchResultViewModel::class.java)

            _binding = FragmentSearchResultBinding.inflate(inflater, container, false)

            val root : View = binding.root

            /*
            binding.btn거리.setOnClickListener{
                findNavContriller().navigate(R.id.action_navigarion_)
            */

            return root
        }
}