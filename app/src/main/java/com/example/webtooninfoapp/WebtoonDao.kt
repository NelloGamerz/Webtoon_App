package com.example.webtooninfoapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WebtoonDao {

    // For managing webtoons with ratings
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWebtoon(webtoon: WebtoonEntity)

    @Query("SELECT * FROM Webtoon WHERE id = :webtoonId")
    suspend fun getWebtoonById(webtoonId: Int): WebtoonEntity?

    @Query("UPDATE Webtoon SET userRating = :userRating WHERE id = :webtoonId")
    suspend fun updateUserRating(webtoonId: Int, userRating: Float)

    @Query("SELECT userRating FROM Webtoon WHERE id = :webtoonId")
    suspend fun getUserRating(webtoonId: Int): Float?

    // For managing favorites separately
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteWebtoon(favoriteWebtoon: FavoriteWebtoonEntity)

    @Query("SELECT * FROM favorites")
    fun getFavoriteWebtoons(): Flow<List<FavoriteWebtoonEntity>>

    @Query("DELETE FROM favorites WHERE webtoonId = :webtoonId")
    suspend fun deleteFavoriteWebtoon(webtoonId: Int)

    // New methods for favorite checks
    @Query("SELECT * FROM favorites WHERE title = :title LIMIT 1")
    suspend fun getFavoriteWebtoonByTitle(title: String): FavoriteWebtoonEntity?

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE webtoonId = :webtoonId)")
    suspend fun isWebtoonFavorite(webtoonId: Int): Boolean

    @Query("SELECT AVG(userRating) FROM Webtoon WHERE Id = :webtoonId")
    fun getAverageRating(webtoonId: Int): Flow<Float?>

    @Query("UPDATE Webtoon SET userRating = :rating WHERE Id = :webtoonId")
    suspend fun updateWebtoonRating(webtoonId: Int, rating: Float)
}
