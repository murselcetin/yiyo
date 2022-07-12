package com.example.yiyo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yiyo.data.entity.SepetYemekler
import com.example.yiyo.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SepetFragmentViewModel @Inject constructor(var yrepo: YemeklerDaoRepository) : ViewModel() {
    var sepettekiYemekListesi = MutableLiveData<List<SepetYemekler>>()

    init {
        sepettekiYemekleriYukle("mursel")
        sepettekiYemekListesi = yrepo.SepettekiYemekleriGetir()
    }

    fun sepettekiYemekleriYukle(kullanici_adi: String) {
        yrepo.sepettekiYemekleriAl(kullanici_adi)
    }

    fun sepettekiYemekSil(sepet_yemek_id: Int, kullanici_adi: String){
        yrepo.sepettekiYemekSil(sepet_yemek_id, kullanici_adi)
    }
}