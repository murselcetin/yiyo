package com.example.yiyo.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.yiyo.data.entity.FavoriYemek
import com.example.yiyo.data.entity.Yemekler
import com.example.yiyo.retrofit.YemeklerDao
import com.example.yiyo.room.FavorilerDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavorilerDaoRepository(var fdao: FavorilerDao) {
    var favoriListesi: MutableLiveData<List<FavoriYemek>>

    init {
        favoriListesi = MutableLiveData()
    }

    fun favorileriGetir(): MutableLiveData<List<FavoriYemek>> {
        return favoriListesi
    }

    fun favoriEkle(favoriYemekResim: String, favoriYemekAdi: String, favoriYemekFiyat: Int) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val favoriYemek = FavoriYemek(0,favoriYemekResim,favoriYemekAdi,favoriYemekFiyat)
            fdao.favoriEkle(favoriYemek)
        }
    }

    fun favoriAra(favoriYemekAdi: String) : Int {
        var favoriKontrol = 0
        val job = CoroutineScope(Dispatchers.Main).launch {
            favoriKontrol = fdao.favoriArama(favoriYemekAdi).size
        }
        return favoriKontrol
    }

    fun favorileriAl() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            favoriListesi.value = fdao.tumFavoriler()
        }
    }
}