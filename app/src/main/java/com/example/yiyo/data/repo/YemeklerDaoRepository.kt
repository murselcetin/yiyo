package com.example.yiyo.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.yiyo.data.entity.Yemekler
import com.example.yiyo.data.entity.YemeklerCevap
import com.example.yiyo.retrofit.YemeklerDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerDaoRepository(var ydao: YemeklerDao) {
    var yemeklerListesi: MutableLiveData<List<Yemekler>>

    init {
        yemeklerListesi = MutableLiveData()
    }

    fun YemekleriGetir(): MutableLiveData<List<Yemekler>>{
        return yemeklerListesi
    }

    fun tumYemekleriAl(){
        ydao.tumYemekler().enqueue(object : Callback<YemeklerCevap>{
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>) {
                val liste = response.body().yemekler
                yemeklerListesi.value = liste
            }
            override fun onFailure(call: Call<YemeklerCevap>?, t: Throwable?) {}
        })
    }

}