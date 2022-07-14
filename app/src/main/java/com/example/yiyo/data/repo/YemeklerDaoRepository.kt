package com.example.yiyo.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.yiyo.data.entity.*
import com.example.yiyo.retrofit.YemeklerDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerDaoRepository(var ydao: YemeklerDao) {
    var yemeklerListesi: MutableLiveData<List<Yemekler>>
    var sepetYemekListesi: MutableLiveData<List<SepetYemekler>>

    init {
        yemeklerListesi = MutableLiveData()
        sepetYemekListesi = MutableLiveData()
    }

    fun YemekleriGetir(): MutableLiveData<List<Yemekler>> {
        return yemeklerListesi
    }

    fun SepettekiYemekleriGetir():MutableLiveData<List<SepetYemekler>>{
        return sepetYemekListesi
    }

    fun siparisKayit(yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String) {
        ydao.sepeteYemekEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
            .enqueue(object : Callback<ESCevap> {
                override fun onResponse(call: Call<ESCevap>?, response: Response<ESCevap>) {
                    val basari = response.body().success
                    val mesaj = response.body().message
                    Log.e("Sipariş Oluştu", "$basari - $mesaj")
                }
                override fun onFailure(call: Call<ESCevap>?, t: Throwable?) {
                }
            })
    }

    fun sepettekiYemekleriAl(kullanici_adi: String){
        ydao.sepettekiYemekleriGetir(kullanici_adi).enqueue(object : Callback<SepetYemeklerCevap>{
            override fun onResponse(call: Call<SepetYemeklerCevap>?, response: Response<SepetYemeklerCevap>) {
                val liste = response.body().sepet_yemekler
                sepetYemekListesi.value = liste
            }
            override fun onFailure(call: Call<SepetYemeklerCevap>?, t: Throwable?) {

            }
        })
    }

    fun sepettekiYemekSil(sepet_yemek_id: Int, kullanici_adi: String){
        ydao.sepettekiYemekSil(sepet_yemek_id, kullanici_adi).enqueue(object : Callback<SepetYemeklerCevap>{
            override fun onResponse(call: Call<SepetYemeklerCevap>?, response: Response<SepetYemeklerCevap>) {
                val basari = response.body().success
                Log.e("Sipariş silindi","$basari")
                sepettekiYemekleriAl(kullanici_adi)
            }
            override fun onFailure(call: Call<SepetYemeklerCevap>?, t: Throwable?) {
            }
        })
    }


    fun tumYemekleriAl() {
        ydao.tumYemekler().enqueue(object : Callback<YemeklerCevap> {
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>) {
                val liste = response.body().yemekler
                yemeklerListesi.value = liste
            }
            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {}
        })
    }

}