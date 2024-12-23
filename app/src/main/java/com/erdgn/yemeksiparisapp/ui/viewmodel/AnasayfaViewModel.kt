package com.erdgn.yemeksiparisapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erdgn.yemeksiparisapp.data.entity.Yemekler
import com.erdgn.yemeksiparisapp.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var yrepo:YemeklerRepository):ViewModel(){
    var yemeklerListesi = MutableLiveData<List<Yemekler>> ()

    init {
        yemekleriYükle()
    }

     fun yemekleriYükle(){
         yemeklerListesi = yrepo.yemekleriYükle()
    }
}