package com.example.newsapp.data.di

import com.example.newsapp.data.database.AppDatabase
import com.example.newsapp.data.database.dao.UserDao
import com.example.newsapp.data.repository.impl.NewsRepositoryImpl
import com.example.newsapp.data.repository.repo.UserRepository
import com.example.newsapp.data.repository.impl.UserRepositoryImpl
import com.example.newsapp.data.repository.repo.NewsRepository
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
    fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository {
        return userRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository {
        return newsRepositoryImpl
    }

}