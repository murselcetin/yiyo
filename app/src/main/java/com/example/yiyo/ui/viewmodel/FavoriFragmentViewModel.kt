package com.example.yiyo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yiyo.data.entity.FavoriYemek
import com.example.yiyo.data.entity.Yemekler
import com.example.yiyo.data.repo.FavorilerDaoRepository
import com.example.yiyo.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriFragmentViewModel @Inject constructor(var frepo: FavorilerDaoRepository) : ViewModel() {
    var favorilerListesi = MutableLiveData<List<FavoriYemek>>()

    init {
        favorilerYukle()
        favorilerListesi = frepo.favorileriGetir()
    }

    fun favorilerYukle() {
        frepo.favorileriAl()
    }

}