package com.erdgn.yemeksiparisapp.data.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.erdgn.yemeksiparisapp.data.entity.SepetYemekler
import com.erdgn.yemeksiparisapp.data.entity.Yemekler
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class YemeklerDataSource(var collectionYemekler: CollectionReference) {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    var sepetYemeklerListesi = MutableLiveData<List<SepetYemekler>>() // Sepet Yemekler için LiveData

    fun yemekleriYükle(): MutableLiveData<List<Yemekler>> {
        collectionYemekler.addSnapshotListener { value, error ->
            if (value != null) {
                val liste = ArrayList<Yemekler>()
                for (d in value.documents) {
                    val yemek = d.toObject(Yemekler::class.java)
                    if (yemek != null) {
                        yemek.yemek_id = d.id
                        liste.add(yemek)
                    }
                }
                yemeklerListesi.value = liste
            }
        }
        return yemeklerListesi
    }

    // Sepete yemek ekleme
    suspend fun sepeteEkle(sepetYemekler: SepetYemekler): Result<Unit> {
        return try {
            // Firestore'a yeni yemek ekle
            val db = FirebaseFirestore.getInstance()
            val sepetCollection: CollectionReference = db.collection("sepet").document("urunler").collection("items")

            // SepetYemekler objesini Firestore'a ekliyoruz
            sepetCollection.add(sepetYemekler).await()

            // Başarı durumu
            Result.success(Unit)
        } catch (e: Exception) {
            // Hata durumu
            Result.failure(e)
        }
    }

    // Sepetteki ürünleri yükleme
    fun sepetYemekleriYükle(kullaniciAdi: String): MutableLiveData<List<SepetYemekler>> {
        val db = FirebaseFirestore.getInstance()
        val sepetCollection: CollectionReference =
            db.collection("sepet").document("urunler").collection("items")

        sepetCollection.whereEqualTo("kullanici_adi", kullaniciAdi)
            .addSnapshotListener { value, error ->
                if (value != null) {
                    val liste = ArrayList<SepetYemekler>()
                    for (d in value.documents) {
                        val sepetYemek = d.toObject(SepetYemekler::class.java)
                        if (sepetYemek != null) {
                            sepetYemek.sepet_yemek_id = d.id
                            liste.add(sepetYemek)
                        }
                    }
                    Log.d("Firestore", "Veri alındı: ${liste.size} öğe")
                    sepetYemeklerListesi.value = liste
                }else{
                    Log.e("Firestore", "Hata: ${error?.message}")
                }
            }
        return sepetYemeklerListesi
    }
}