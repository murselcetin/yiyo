package com.example.yiyo.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yiyo.data.entity.FavoriYemek
import com.example.yiyo.retrofit.YemeklerDao

@Database(entities = [FavoriYemek::class], version = 1)
abstract class RoomVeritabani : RoomDatabase() {
    abstract fun favorileriGetir() : YemeklerDao
}