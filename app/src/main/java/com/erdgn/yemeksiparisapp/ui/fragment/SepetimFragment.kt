package com.erdgn.yemeksiparisapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.erdgn.yemeksiparisapp.R
import com.erdgn.yemeksiparisapp.databinding.FragmentSepetimBinding
import com.erdgn.yemeksiparisapp.ui.adapter.YemeklerAdapter
import com.erdgn.yemeksiparisapp.ui.viewmodel.SepetimViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetimFragment : Fragment() {
    private lateinit var binding: FragmentSepetimBinding
    private lateinit var viewModel: SepetimViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sepetim,container,false)

        binding.sepetimToolbarBaslik ="Sepetim"


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:SepetimViewModel by viewModels()
        viewModel = tempViewModel
    }

}