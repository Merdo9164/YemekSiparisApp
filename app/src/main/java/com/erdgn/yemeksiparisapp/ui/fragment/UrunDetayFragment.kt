package com.erdgn.yemeksiparisapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.erdgn.yemeksiparisapp.R
import com.erdgn.yemeksiparisapp.databinding.FragmentUrunDetayBinding
import com.erdgn.yemeksiparisapp.ui.viewmodel.SepetimViewModel
import com.erdgn.yemeksiparisapp.ui.viewmodel.UrunDetayViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UrunDetayFragment : Fragment() {
    private lateinit var binding: FragmentUrunDetayBinding
    private lateinit var viewModel: UrunDetayViewModel
    @SuppressLint("DiscouragedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUrunDetayBinding.inflate(inflater, container, false)

        binding.toolbarUrunDetay.title= "Ürün Detayı"

        val bundle : UrunDetayFragmentArgs by navArgs()
        val yemek = bundle.yemek

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(this).load(url).override(500,750).into(binding.ivYemek)

        binding.textViewFiyat.text = "${yemek.yemek_fiyat} ₺"

        binding.textViewYemekAd.text = "${yemek.yemek_adi}"

        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: UrunDetayViewModel by viewModels()
        viewModel = tempViewModel
    }
}