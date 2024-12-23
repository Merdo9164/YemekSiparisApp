package com.erdgn.yemeksiparisapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erdgn.yemeksiparisapp.data.entity.SepetUrunler


class SharedViewModel : ViewModel() {
    //ortak verileri burda kullanıcam
    val sepetUrunler = MutableLiveData<ArrayList<SepetUrunler>>()

    fun siparisArttir(yemek_adi: String, yemek_adet: Int) {
        sepetUrunler.value?.add(
            SepetUrunler(
                yemek_adi = yemek_adi,
                yemek_adet = yemek_adet
            )
        )

    }

    fun siparisAzalt(yemek: SepetUrunler) {
        sepetUrunler.value?.remove(yemek)
    }

}