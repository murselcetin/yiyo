package com.example.yiyo.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.yiyo.data.entity.FavoriYemek

@Dao
interface FavorilerDao {
    @Query("SELECT * FROM favoriler")
    suspend fun tumFavoriler(): List<FavoriYemek>

    @Insert
    suspend fun favoriEkle(favoriYemek: FavoriYemek)

    @Delete
    suspend fun favoriSil(favoriYemek: FavoriYemek)

    @Query("SELECT * FROM favoriler WHERE yemek_adi like '%' ||:favoriYemekAdi|| '%'")
    suspend fun favoriArama(favoriYemekAdi: String): List<FavoriYemek>
}