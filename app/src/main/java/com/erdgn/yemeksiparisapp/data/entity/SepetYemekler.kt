package com.erdgn.yemeksiparisapp.data.entity

import com.google.firebase.firestore.DocumentId
import java.io.Serializable

data class SepetYemekler(
    @DocumentId var sepet_yemek_id: String? = "",  // Firestore belge ID'sini alacak
    var yemek_adi: String? = "",
    var yemek_resim_adi: String? = "",
    var yemek_fiyat: Int ?=0,
    var yemek_siparis_adet: Int ?= 0,
    var kullanici_adi: String? = ""
) : Serializable
