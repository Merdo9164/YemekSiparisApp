package com.erdgn.yemeksiparisapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.erdgn.yemeksiparisapp.R
import com.erdgn.yemeksiparisapp.data.entity.Yemekler
import com.erdgn.yemeksiparisapp.databinding.FragmentAnasayfaBinding
import com.erdgn.yemeksiparisapp.ui.adapter.YemeklerAdapter
import com.erdgn.yemeksiparisapp.ui.viewmodel.AnasayfaViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var viewModel: AnasayfaViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa , container, false)
        binding.anasayfaToolbarBaslik = "Yemekler"


        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            val yemeklerAdapter = YemeklerAdapter(requireContext(),it)
            binding.yemeklerAdapter = yemeklerAdapter
        }

        binding.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.sepetimGecis)
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:AnasayfaViewModel by viewModels ()
        viewModel = tempViewModel
    }

}