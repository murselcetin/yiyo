package com.example.yiyo.di

import com.example.yiyo.data.repo.YemeklerDaoRepository
import com.example.yiyo.retrofit.ApiUtils
import com.example.yiyo.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}