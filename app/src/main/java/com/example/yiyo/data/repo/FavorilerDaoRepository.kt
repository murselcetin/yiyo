package com.example.yiyo.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.yiyo.data.entity.FavoriYemek
import com.example.yiyo.data.entity.Yemekler
import com.example.yiyo.retrofit.YemeklerDao
import com.example.yiyo.room.FavorilerDao
import com.example.yiyo.util.MySharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavorilerDaoRepository @Inject constructor(
    var fdao: FavorilerDao,
    val prefs: MySharedPreferences
) {
    var favoriListesi: MutableLiveData<List<FavoriYemek>>

    init {
        favoriListesi = MutableLiveData()
    }

    fun favorileriGetir(): MutableLiveData<List<FavoriYemek>> {
        return favoriListesi
    }


    fun favoriEkle(favoriYemekResim: String, favoriYemekAdi: String, favoriYemekFiyat: Int) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val favoriYemek = FavoriYemek(
                0,
                favoriYemekResim,
                favoriYemekAdi,
                favoriYemekFiyat,
                prefs.kullaniciAdi ?: ""
            )
            fdao.favoriEkle(favoriYemek)
        }
    }

    fun favoriSil(
        favoriId: Int,
        favoriYemekResim: String,
        favoriYemekAdi: String,
        favoriYemekFiyat: Int
    ) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val favoriYemek = FavoriYemek(
                favoriId,
                favoriYemekResim,
                favoriYemekAdi,
                favoriYemekFiyat,
                prefs.kullaniciAdi ?: ""
            )
            fdao.favoriSil(favoriYemek)
        }
    }

    suspend fun favoriArama(favoriYemekAdi: String): List<FavoriYemek> {
        return fdao.favoriArama(favoriYemekAdi, prefs.kullaniciAdi ?: "")
    }

    suspend fun favoriId(favoriYemekAdi: String): FavoriYemek {
        return fdao.favoriId(favoriYemekAdi, prefs.kullaniciAdi ?: "")
    }


    fun favorileriAl() {
        val job = CoroutineScope(Dispatchers.Main).launch {
            favoriListesi.value = fdao.tumFavoriler(prefs.kullaniciAdi ?: "")
        }
    }
}