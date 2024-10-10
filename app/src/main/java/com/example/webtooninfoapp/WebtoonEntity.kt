package com.example.webtooninfoapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "webtoon") // Use 'webtoon' for storing webtoon data with ratings
data class WebtoonEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val imageUrl: String,
    val userRating: Float = 0.0f // Add a field for user ratings, default is 0
)
