package com.example.yiyo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yiyo.data.entity.Kullanici
import com.example.yiyo.data.entity.Yemekler
import com.example.yiyo.data.repo.KullaniciDaoRepository
import com.example.yiyo.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GirisFragmentViewModel @Inject constructor(var krepo: KullaniciDaoRepository) :
    ViewModel() {
    var kullaniciListesi = MutableLiveData<List<Kullanici>>()

    fun kullaniciKontrol(kullanici: Kullanici) {
        viewModelScope.launch {
            kullaniciListesi.value =  krepo.kullaniciAl(kullanici)
        }
    }

}
