package com.example.newsapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.database.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User): Long


    @Query("SELECT * FROM User WHERE email=:email AND password = :password")
    suspend fun getUser(email: String, password:String): User?

}