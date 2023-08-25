package com.example.newsapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.database.AppDatabase
import com.example.newsapp.data.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context):AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java,"database").build()

    @Provides
    fun provideUserDao(appDatabase: AppDatabase):UserDao = appDatabase.userDao()

}