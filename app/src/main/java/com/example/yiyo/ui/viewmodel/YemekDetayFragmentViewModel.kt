package com.example.yiyo.ui.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yiyo.data.entity.FavoriYemek
import com.example.yiyo.data.entity.SepetYemekler
import com.example.yiyo.data.entity.Yemekler
import com.example.yiyo.data.repo.FavorilerDaoRepository
import com.example.yiyo.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YemekDetayFragmentViewModel @Inject constructor(var yrepo: YemeklerDaoRepository,var frepo:FavorilerDaoRepository) : ViewModel() {
    var yemekAdi: String = ""
    var yemekSiparisId:Int = 0
    var sepettekiYemekListesi = MutableLiveData<List<SepetYemekler>>()
    val sepetSayi = MutableLiveData<Int>()

    init {
        sepettekiYemekleriYukle("mursel")
        sepettekiYemekListesi = yrepo.SepettekiYemekleriGetir()
    }

    fun sepettekiYemekleriYukle(kullanici_adi: String) {
        yrepo.sepettekiYemekleriAl(kullanici_adi)
    }

    fun sepeteEkle(yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi:String) {
        yemekAdetGetir { itAdet ->
            siparisIdGetir{itId ->
                oncekiSiparisSil(itId,kullanici_adi)
                yrepo.siparisKayit(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet,kullanici_adi)
            }
        }
    }

    fun favoriKayit(favoriYemekResim: String, favoriYemekAdi: String, favoriYemekFiyat: Int) {
        frepo.favoriEkle(favoriYemekResim,favoriYemekAdi,favoriYemekFiyat)
    }

    fun yemekAdetGetir(sepetYemekAdet: (result: Int) -> Unit){
        val liste = sepettekiYemekListesi.value
        val yemekAdet = liste?.filter { it.yemek_adi.contentEquals(yemekAdi) }?.getOrNull(0)?.yemek_siparis_adet?:0
        sepetSayi.value = yemekAdet
        sepetYemekAdet.invoke(yemekAdet)
    }

    fun oncekiSiparisSil(sepet_yemek_id: Int, kullanici_adi: String){
        yrepo.sepettekiYemekSil(sepet_yemek_id, kullanici_adi)
    }

    fun siparisIdGetir(sepetYemekId: (result: Int) -> Unit){
        val liste = sepettekiYemekListesi.value
        val yemekId = liste?.filter { it.yemek_adi.contains(yemekAdi) }?.getOrNull(0)?.sepet_yemek_id?:0
        sepetYemekId.invoke(yemekId)
    }

    fun siparisYemekAdiGetir(sepetYemekAdi: (result: String) -> Unit){
        val liste = sepettekiYemekListesi.value
        val yemekAdi = liste?.filter { it.yemek_adi == yemekAdi }?.getOrNull(0)?.yemek_adi?:""
        sepetYemekAdi.invoke(yemekAdi)
    }
}