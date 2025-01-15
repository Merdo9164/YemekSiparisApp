package com.erdgn.yemeksiparisapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.erdgn.yemeksiparisapp.data.entity.SepetYemekler
import com.erdgn.yemeksiparisapp.databinding.CardTasarimSepetBinding


class SepetAdapter(
    private val mContext: Context,
    private var sepetListesi: List<SepetYemekler>
) : RecyclerView.Adapter<SepetAdapter.CardTasarimTutucu>() {


    inner class CardTasarimTutucu(val sepettasarim: CardTasarimSepetBinding) :
        RecyclerView.ViewHolder(sepettasarim.root)

    private var sepetSil: ((SepetYemekler) -> Unit)? = null

    fun setsepetSil(listener: (SepetYemekler) -> Unit) {
        sepetSil = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding = CardTasarimSepetBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val sepetYemek = sepetListesi[position]
        val t = holder.sepettasarim

        t.sepetCardYemekAd.text = sepetYemek.yemek_adi
        t.sepetCardFiyat.text = "${sepetYemek.yemek_fiyat} ₺"
        t.sepetCardAdet.text = "${sepetYemek.yemek_siparis_adet}"
        t.sepetCardToplamFiyat.text =
            "${sepetYemek.yemek_fiyat!! * sepetYemek.yemek_siparis_adet!!} ₺"

        // Görsel yükleme (Opsiyonel)
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${sepetYemek.yemek_resim_adi}"
        Glide.with(mContext).load(url).override(500, 750).into(t.sepetCardResim)

        t.sepetCardFab.setOnClickListener {
            sepetSil?.invoke(sepetYemek)
        }
    }
    override fun getItemCount(): Int = sepetListesi.size

    fun setSepetYemekler(sepetYemekler: List<SepetYemekler>) {
        this.sepetListesi = sepetYemekler
        notifyDataSetChanged() // Veriler değiştiğinde RecyclerView'ı güncelliyoruz
    }

}

