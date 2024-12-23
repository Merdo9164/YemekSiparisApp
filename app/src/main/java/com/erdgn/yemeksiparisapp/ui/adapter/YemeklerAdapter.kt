package com.erdgn.yemeksiparisapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.erdgn.yemeksiparisapp.data.entity.Yemekler
import com.erdgn.yemeksiparisapp.databinding.CardTasarimBinding
import com.erdgn.yemeksiparisapp.databinding.CardTasarimSepetBinding
import com.erdgn.yemeksiparisapp.databinding.FragmentAnasayfaBinding
import com.erdgn.yemeksiparisapp.ui.fragment.AnasayfaFragmentDirections
import com.google.android.material.snackbar.Snackbar

class YemeklerAdapter (var mContext: Context , var yemeklerListesi:List<Yemekler>)
    :RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>(){

    inner class CardTasarimTutucu (var tasarim:CardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding = CardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimTutucu(binding)
    }
    @SuppressLint("DiscouragedApi")
    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemeklerListesi.get(position)
        val t = holder.tasarim

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(500,750).into(t.imageViewAYemek)

        t.textViewAYemekAd.text = "${yemek.yemek_adi}"

        t.textViewAFiyat.text = "${yemek.yemek_fiyat} ₺"


        t.cardViewYemek.setOnClickListener {
            val gecis =AnasayfaFragmentDirections.urunDetayGecis(yemek = yemek)
            Navigation.findNavController(it).navigate(gecis)

        }
        t.buttonASepet.setOnClickListener {
            Snackbar.make(it,"${yemek.yemek_adi} sepete eklendi",Snackbar.LENGTH_SHORT).show()
        }
    }
    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }
}