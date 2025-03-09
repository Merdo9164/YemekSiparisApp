package com.erdgn.yemeksiparisapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erdgn.yemeksiparisapp.data.entity.SepetUrunler


class SharedViewModel : ViewModel() {
    private val _sepetUrunler = MutableLiveData<MutableList<SepetUrunler>>(mutableListOf())

    fun siparisArttir(yemek_adi: String?, adet: Int) {
        val urun = _sepetUrunler.value?.find { it.yemek_adi == yemek_adi }
        if (urun != null) {
            urun.yemek_adet = adet + 1
        } else {
            _sepetUrunler.value?.add(SepetUrunler(yemek_adi, adet))
        }
        _sepetUrunler.value = _sepetUrunler.value // Listeyi yeniden tetiklemek için
    }

    fun siparisAzalt(yemek_adi: String?, adet: Int) {
        val urun = _sepetUrunler.value?.find { it.yemek_adi == yemek_adi }
        if (urun != null) {
            urun.yemek_adet = adet-1
            if (urun.yemek_adet!! <= 0) {
                _sepetUrunler.value?.remove(urun)
            }
        }
        _sepetUrunler.value = _sepetUrunler.value // Listeyi yeniden tetiklemek için
    }

}