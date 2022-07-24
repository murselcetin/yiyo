package com.example.yiyo.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.yiyo.data.repo.YemeklerDaoRepository
import com.example.yiyo.util.MySharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SepetOnayFragmentViewModel @Inject constructor(var yrepo: YemeklerDaoRepository, var prefs: MySharedPreferences) :
    ViewModel() {

    var sepetIdList = ArrayList<Int>()

    fun tumYemekleriSil(){
        sepetIdList.forEach {
            yrepo.sepettekiYemekSil(it, prefs.kullaniciAdi?:"")
        }
    }
}