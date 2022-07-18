package com.example.yiyo.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.yiyo.data.entity.Kullanici
import com.example.yiyo.data.repo.KullaniciDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KayitFragmentViewModel @Inject constructor(var krepo: KullaniciDaoRepository) :
    ViewModel() {
    fun kullaniciKaydet(kullaniciAdi: String, sifre: String) {
        krepo.kullaniciKayit(kullaniciAdi, sifre)
    }
}