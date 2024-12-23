package com.erdgn.yemeksiparisapp.data.repo

import androidx.lifecycle.MutableLiveData
import com.erdgn.yemeksiparisapp.data.datasource.YemeklerDataSource
import com.erdgn.yemeksiparisapp.data.entity.Yemekler

class YemeklerRepository(var yds :YemeklerDataSource) {

     fun yemekleriYükle() : MutableLiveData<List<Yemekler>> = yds.yemekleriYükle()
}