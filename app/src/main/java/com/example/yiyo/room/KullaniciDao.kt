package com.example.yiyo.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.yiyo.data.entity.FavoriYemek
import com.example.yiyo.data.entity.Kullanici

@Dao
interface KullaniciDao {
    @Query("SELECT * FROM kullanicilar WHERE kullanici_adi = :kullaniciAdi LIMIT 1")
    suspend fun kullanici(kullaniciAdi: String): List<Kullanici>

    @Insert
    suspend fun kullaniciKayit(kullanici: Kullanici)
}