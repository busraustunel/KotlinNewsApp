package com.example.newsapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.Constants
import com.example.newsapp.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context):AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, Constants.DATABASE_NAME).build()
    }

}