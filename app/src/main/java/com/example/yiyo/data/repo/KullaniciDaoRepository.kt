package com.example.yiyo.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.yiyo.data.entity.FavoriYemek
import com.example.yiyo.data.entity.Kullanici
import com.example.yiyo.room.FavorilerDao
import com.example.yiyo.room.KullaniciDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class KullaniciDaoRepository @Inject constructor(var kdao: KullaniciDao) {

    var kullaniciListesi: MutableLiveData<List<Kullanici>>

    init {
        kullaniciListesi = MutableLiveData()
    }

    fun kullaniciGetir(): MutableLiveData<List<Kullanici>> {
        return kullaniciListesi
    }

    suspend fun kullaniciAl(kullanici: Kullanici): List<Kullanici> {
        return kdao.kullanici(kullanici.kullanici_adi)
    }

    fun kullaniciKayit(kullaniciAdi: String, sifre: String) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val kullanici = Kullanici(0, kullaniciAdi, sifre)
            Log.e("Kayit", kullanici.toString())
            kdao.kullaniciKayit(kullanici)
        }
    }
}