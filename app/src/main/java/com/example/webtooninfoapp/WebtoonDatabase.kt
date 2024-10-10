package com.example.webtooninfoapp

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [WebtoonEntity::class, FavoriteWebtoonEntity::class], version = 2, exportSchema = false)
abstract class WebtoonDatabase : RoomDatabase() {
    abstract fun webtoonDao(): WebtoonDao

    companion object {
        @Volatile
        private var INSTANCE: WebtoonDatabase? = null

        fun getDatabase(context: Context): WebtoonDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WebtoonDatabase::class.java,
                    "webtoon_database"
                )
                    .fallbackToDestructiveMigration()  // This line will drop and recreate the table if schema changes
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
