package com.example.yiyo.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.yiyo.data.entity.FavoriYemek
import com.example.yiyo.data.entity.Kullanici

@Dao
interface FavorilerDao {
    @Query("SELECT * FROM favoriler WHERE kullanici_adi = :kullaniciAdi")
    suspend fun tumFavoriler(kullaniciAdi: String): List<FavoriYemek>

    @Insert
    suspend fun favoriEkle(favoriYemek: FavoriYemek)

    @Delete
    suspend fun favoriSil(favoriYemek: FavoriYemek)

    @Query("SELECT * FROM favoriler WHERE yemek_adi = :favoriYemekAdi AND kullanici_adi = :kullaniciAdi LIMIT 1")
    suspend fun favoriArama(favoriYemekAdi: String, kullaniciAdi: String): List<FavoriYemek>

    @Query("SELECT * FROM favoriler WHERE yemek_adi = :favoriYemekAdi AND kullanici_adi = :kullaniciAdi LIMIT 1")
    suspend fun favoriId(favoriYemekAdi: String, kullaniciAdi: String): FavoriYemek
}