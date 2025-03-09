package com.erdgn.yemeksiparisapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.erdgn.yemeksiparisapp.databinding.FragmentUrunDetayBinding
import com.erdgn.yemeksiparisapp.ui.viewmodel.SepetimViewModel
import com.erdgn.yemeksiparisapp.ui.viewmodel.SharedViewModel
import com.erdgn.yemeksiparisapp.ui.viewmodel.UrunDetayViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UrunDetayFragment : Fragment() {
    private lateinit var binding: FragmentUrunDetayBinding
    private lateinit var urunDetayViewModel: UrunDetayViewModel
    private val sharedViewModel: SharedViewModel by viewModels() // SharedViewModel'e bağlanıyoruz
    private val sepetimViewModel: SepetimViewModel by viewModels ()

    @SuppressLint("DiscouragedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUrunDetayBinding.inflate(inflater, container, false)

        // Toolbar başlığını ayarla
        binding.toolbarUrunDetay.title = "Ürün Detayı"

        // Fragment argümanlarını al
        val bundle: UrunDetayFragmentArgs by navArgs()
        val yemek = bundle.yemek

        // Görseller ve bilgileri doldur
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(this).load(url).override(500, 750).into(binding.ivYemek)
        binding.textViewFiyat.text = "${yemek.yemek_fiyat} ₺"
        binding.textViewYemekAd.text = "${yemek.yemek_adi}"

        // Varsayılan adet değeri 1 olarak başlatılır
        var adet = 1
        binding.textViewAdet.text = adet.toString()

        // Artırma butonu tıklama işlemi
        binding.fabArttir.setOnClickListener {
            adet++
            binding.textViewAdet.text = adet.toString()
            sharedViewModel.siparisArttir(yemek.yemek_adi, 1) // SharedViewModel'e aktar
            binding.textViewToplamFiyat.text = "${yemek.yemek_fiyat?.times(adet)} ₺"
        }

        // Azaltma butonu tıklama işlemi
        binding.fabAzalt.setOnClickListener {
            if (adet > 1) {
                adet--
                binding.textViewAdet.text = adet.toString()
                sharedViewModel.siparisAzalt(yemek.yemek_adi, 1) // SharedViewModel'e aktar
            }
            binding.textViewToplamFiyat.text = "${yemek.yemek_fiyat?.times(adet)} ₺"
        }

        binding.textViewToplamFiyat.text = "${yemek.yemek_fiyat?.times(adet)} ₺"

        // Sepete Ekle butonu
        binding.buttonSepeteEkle.setOnClickListener {
            sepetimViewModel.sepeteEkle(
                yemek_adi = yemek.yemek_adi,
                yemek_resim_adi = yemek.yemek_resim_adi,
                yemek_fiyat = yemek.yemek_fiyat,
                yemek_siparis_adet = adet,
                kullanici_adi = "erdgn", // Kullanıcı adı dinamik hale getirilebilir
                onSuccess = {
                    Toast.makeText(requireContext(), "Ürün sepete eklendi!", Toast.LENGTH_SHORT).show()
                },
                onError = { error ->
                    Toast.makeText(requireContext(), "Hata: $error", Toast.LENGTH_SHORT).show()
                }
            )
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: UrunDetayViewModel by viewModels()
        urunDetayViewModel = tempViewModel
    }
}
