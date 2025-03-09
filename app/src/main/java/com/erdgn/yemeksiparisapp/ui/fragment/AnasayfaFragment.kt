package com.erdgn.yemeksiparisapp.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.OptIn
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.erdgn.yemeksiparisapp.R
import com.erdgn.yemeksiparisapp.data.entity.Yemekler
import com.erdgn.yemeksiparisapp.databinding.FragmentAnasayfaBinding
import com.erdgn.yemeksiparisapp.ui.adapter.YemeklerAdapter
import com.erdgn.yemeksiparisapp.ui.viewmodel.AnasayfaViewModel
import com.erdgn.yemeksiparisapp.ui.viewmodel.SepetimViewModel
import com.erdgn.yemeksiparisapp.ui.viewmodel.SharedViewModel
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var viewModel: AnasayfaViewModel
    private val viewModels: SepetimViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_anasayfa, container, false)
        binding.anasayfaToolbarBaslik = "Yemekler"


        viewModel.yemeklerListesi.observe(viewLifecycleOwner) {yemeklerListesi ->
            val yemeklerAdapter = YemeklerAdapter(mContext = requireContext(),
                yemeklerListesi = yemeklerListesi,
                onSepeteEkle = { yemek ->
                    viewModels.sepeteEkle(
                        yemek_adi = yemek.yemek_adi,
                        yemek_resim_adi = yemek.yemek_resim_adi,
                        yemek_fiyat = yemek.yemek_fiyat,
                        yemek_siparis_adet = 1, // Örnek olarak 1 adet ekleniyor
                        kullanici_adi = "erdgn", // Örnek kullanıcı adı
                        onSuccess = {
                            Snackbar.make(requireView(), "${yemek.yemek_adi} sepete eklendi", Snackbar.LENGTH_SHORT).show()
                        },
                        onError = { errorMessage ->
                            Snackbar.make(requireView(), "Hata: $errorMessage", Snackbar.LENGTH_SHORT).show()
                        }
                    )
                }
            )
            binding.yemeklerAdapter = yemeklerAdapter
        }

        viewModels.sepetYemekleriYükle("erdgn") // live data tetikleniyor
        // Sepet öğelerinin adedini takip etme
        viewModels.sepetYemeklerListesi.observe(viewLifecycleOwner) { sepetYemekler ->
            val toplamAdet = sepetYemekler.sumOf { it.yemek_siparis_adet ?:0 }
            updateFabBadge(toplamAdet) // FAB badge'i güncelliyoruz
        }


        binding.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.sepetimGecis)
        }

        return binding.root
    }

    // FAB badge güncelleme
    private fun updateFabBadge(toplamAdet: Int) {
        val badgeTextView = binding.root.findViewById<TextView>(R.id.badge)
        if (toplamAdet > 0) {
            badgeTextView.visibility = View.VISIBLE
            badgeTextView.text = toplamAdet.toString() // Adeti güncelle
        } else {
            badgeTextView.visibility = View.INVISIBLE // Adet 0 ise, badge gizle
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AnasayfaViewModel by viewModels()
        viewModel = tempViewModel
    }

}