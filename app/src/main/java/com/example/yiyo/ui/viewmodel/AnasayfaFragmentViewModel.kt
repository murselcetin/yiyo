package com.example.yiyo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yiyo.data.entity.Yemekler
import com.example.yiyo.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnasayfaFragmentViewModel @Inject constructor(var yrepo: YemeklerDaoRepository) :
    ViewModel() {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    init {
        yemeklerYukle()
        yemeklerListesi = yrepo.YemekleriGetir()
    }

    fun yemeklerYukle() {
        yrepo.tumYemekleriAl()
    }
}