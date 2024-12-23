package com.erdgn.yemeksiparisapp.data.entity

import java.io.Serializable

data class Yemekler (var yemek_id:String? = "",
                     var yemek_adi:String? = "",
                     var yemek_resim_adi:String? = "",
                     var yemek_fiyat:Int ?=0 ):Serializable
