package com.example.newsapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String? = null,
    val surname: String? = null,
    val email: String?,
    val password: String?
)