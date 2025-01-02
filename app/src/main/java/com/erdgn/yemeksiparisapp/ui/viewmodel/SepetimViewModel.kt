package com.erdgn.yemeksiparisapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erdgn.yemeksiparisapp.data.entity.SepetYemekler
import com.erdgn.yemeksiparisapp.data.repo.YemeklerRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SepetimViewModel @Inject constructor(private val repository: YemeklerRepository) :
    ViewModel() {


    // Sepete yemek ekleme
    fun sepeteEkle(
        yemek_adi: String?,
        yemek_resim_adi: String?,
        yemek_fiyat: Int?,
        yemek_siparis_adet: Int,
        kullanici_adi: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val sepetYemek = SepetYemekler(
                yemek_adi = yemek_adi,
                yemek_resim_adi = yemek_resim_adi,
                yemek_fiyat = yemek_fiyat,
                yemek_siparis_adet = yemek_siparis_adet,
                kullanici_adi = kullanici_adi
            )

            // Sepet ekleme işlemi
            val result = repository.sepeteEkle(sepetYemek)
            if (result.isSuccess) {
                onSuccess()
            } else {
                onError(result.exceptionOrNull()?.message ?: "Bilinmeyen bir hata oluştu.")
            }
        }
    }

    val sepetYemeklerListesi = MutableLiveData<List<SepetYemekler>>()


    // Sepetteki ürünleri yükleme
    fun sepetYemekleriYükle(kullaniciAdi: String) {
        repository.sepetYemekleriYükle(kullaniciAdi).observeForever { sepetYemekler ->
            sepetYemeklerListesi.value = sepetYemekler
        }
    }

}
