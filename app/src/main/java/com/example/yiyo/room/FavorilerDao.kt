package com.example.yiyo.room

import androidx.room.Dao
import androidx.room.Query
import com.example.yiyo.data.entity.FavoriYemek

@Dao
interface FavorilerDao {
    @Query("SELECT * FROM favoriler")
    suspend fun tumFavoriler(): List<FavoriYemek>
}