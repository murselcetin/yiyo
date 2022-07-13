package com.example.yiyo.di

import android.content.Context
import androidx.room.Room
import com.example.yiyo.data.repo.YemeklerDaoRepository
import com.example.yiyo.retrofit.ApiUtils
import com.example.yiyo.retrofit.YemeklerDao
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
    fun provideYemeklerDao2(@ApplicationContext context: Context): YemeklerDao{
        val vt = Room.databaseBuilder(context,RoomVeritabani::class.java,"yiyo.sqlite").createFromAsset("yiyo.sqlite").build()
        return vt.favorileriGetir()
    }
}