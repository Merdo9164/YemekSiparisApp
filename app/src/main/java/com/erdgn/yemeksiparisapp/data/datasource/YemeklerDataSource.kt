package com.erdgn.yemeksiparisapp.data.datasource

import androidx.lifecycle.MutableLiveData
import com.erdgn.yemeksiparisapp.data.entity.Yemekler
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource(var collectionYemekler: CollectionReference) {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

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



}