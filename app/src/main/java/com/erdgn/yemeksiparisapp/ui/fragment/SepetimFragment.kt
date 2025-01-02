package com.erdgn.yemeksiparisapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.erdgn.yemeksiparisapp.R
import com.erdgn.yemeksiparisapp.data.entity.SepetYemekler
import com.erdgn.yemeksiparisapp.databinding.FragmentSepetimBinding
import com.erdgn.yemeksiparisapp.ui.adapter.SepetAdapter
import com.erdgn.yemeksiparisapp.ui.viewmodel.SepetimViewModel
import dagger.hilt.android.AndroidEntryPoint




@AndroidEntryPoint
class SepetimFragment : Fragment() {

    private var _binding: FragmentSepetimBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SepetimViewModel by viewModels()
    private lateinit var adapter: SepetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSepetimBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Toolbar başlığı
        binding.sepetimToolbarBaslik = "Sepetim"

        val kullaniciAdi = "erdgn" // Dinamik olarak alabilirsiniz.
        // RecyclerView setup
        val sepetadapter = SepetAdapter(requireContext(), listOf())
        binding.sepetRv.layoutManager = LinearLayoutManager(requireContext())
        binding.sepetAdapter = sepetadapter

        // Firestore'dan verileri çek ve göster

        viewModel.sepetYemeklerListesi.observe(viewLifecycleOwner) { sepetYemekler ->
            if (sepetYemekler.isNotEmpty()) {
                sepetadapter.setSepetYemekler(sepetYemekler) // Yeni veriyi adapter'a set ediyoruz
                binding.sepetBosText.visibility = View.GONE
            } else {
                binding.sepetBosText.visibility = View.VISIBLE
            }


            val toplamFiyat = sepetYemekler.sumOf { it.yemek_fiyat!! * it.yemek_siparis_adet }
            binding.textViewSepetToplam.text = "$toplamFiyat ₺"
        }

        viewModel.sepetYemekleriYükle(kullaniciAdi)

        // Sepeti onayla butonu
        binding.buttonSepetiOnayla.setOnClickListener {
            // Burada sepeti onaylama işlemleri yapılabilir.
            // Örnek: verileri sunucuya gönderme.
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}