package com.example.newsapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
    val value:String

    /* DATABASE EKLEME:
    INSERT INTO Category (name, value)
    VALUES
    ('All', 'all'),
    ('National', 'national'),
    ('Business', 'business'),
    ('Sports', 'sports'),
    ('World', 'world'),
    ('Politics', 'politics'),
    ('Technology', 'technology'),
    ('Startup', 'startup'),
    ('Entertainment', 'entertainment'),
    ('Miscellaneous', 'miscellaneous'),
    ('Hatke', 'hatke'),
    ('Science', 'science'),
    ('Automobile', 'automobile');
     */

)