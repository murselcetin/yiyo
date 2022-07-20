package com.example.yiyo.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yiyo.data.repo.FavorilerDaoRepository
import com.example.yiyo.data.repo.KullaniciDaoRepository
import com.example.yiyo.data.repo.YemeklerDaoRepository
import com.example.yiyo.retrofit.ApiUtils
import com.example.yiyo.retrofit.YemeklerDao
import com.example.yiyo.room.FavorilerDao
import com.example.yiyo.room.KullaniciDao
import com.example.yiyo.room.RoomVeritabani
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideYemeklerDaoRepository(ydao: YemeklerDao): YemeklerDaoRepository {
        return YemeklerDaoRepository(ydao)
    }

    @Provides
    @Singleton
    fun provideYemeklerDao(): YemeklerDao{
        return ApiUtils.getYemeklerDao()
    }


    @Provides
    @Singleton
    fun provideMyDatabase(@ApplicationContext context: Context) =
         Room.databaseBuilder(context, RoomVeritabani::class.java,"yiyo.sqlite").createFromAsset("yiyo.sqlite").build()


    @Provides
    @Singleton
    fun provideFavorilerDao(db:RoomVeritabani) = db.favorileriGetir()


    @Provides
    @Singleton
    fun provideKullaniciDao(db:RoomVeritabani) = db.kullaniciGetir()

    @Provides
    @Singleton
    fun mySharedPreferences(@ApplicationContext app: Context): SharedPreferences {
        return app.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    }
}