package com.example.newsapp.data.di

import com.example.newsapp.data.database.AppDatabase
import com.example.newsapp.data.database.dao.UserDao
import com.example.newsapp.data.database.repository.UserRepository
import com.example.newsapp.data.database.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase):UserDao {
         return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl):UserRepository {
        return userRepositoryImpl
    }

}