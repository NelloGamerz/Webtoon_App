package com.example.webtooninfoapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites") // Table to store favorite webtoons
data class FavoriteWebtoonEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val webtoonId: Int,  // Foreign key linking to the webtoon table
    val title: String,
    val description: String,
    val imageUrl: String
)
