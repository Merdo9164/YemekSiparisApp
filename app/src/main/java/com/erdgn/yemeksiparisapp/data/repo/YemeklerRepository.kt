package com.erdgn.yemeksiparisapp.data.repo

import androidx.lifecycle.MutableLiveData
import com.erdgn.yemeksiparisapp.data.datasource.YemeklerDataSource
import com.erdgn.yemeksiparisapp.data.entity.SepetYemekler
import com.erdgn.yemeksiparisapp.data.entity.Yemekler

class YemeklerRepository(var yds: YemeklerDataSource) {

    fun yemekleriYükle(): MutableLiveData<List<Yemekler>> = yds.yemekleriYükle()

    // Sepete yemek ekleme
    suspend fun sepeteEkle(sepetYemekler: SepetYemekler): Result<Unit> {
        return yds.sepeteEkle(sepetYemekler)
    }


    fun sepetYemekleriYükle(kullanici_adi: String): MutableLiveData<List<SepetYemekler>> {
        return yds.sepetYemekleriYükle(kullanici_adi)
    }
}