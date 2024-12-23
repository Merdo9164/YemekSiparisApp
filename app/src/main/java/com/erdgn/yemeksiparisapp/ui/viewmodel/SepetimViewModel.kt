package com.erdgn.yemeksiparisapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erdgn.yemeksiparisapp.data.entity.Yemekler
import com.erdgn.yemeksiparisapp.data.repo.YemeklerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SepetimViewModel @javax.inject.Inject constructor(var yrepo:YemeklerRepository):ViewModel(){

}