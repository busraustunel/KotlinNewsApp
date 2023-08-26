package com.example.newsapp.data.di

import com.example.newsapp.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit():Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Constants.BASE_URL).build()
    }
}