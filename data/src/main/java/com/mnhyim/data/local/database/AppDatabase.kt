package com.mnhyim.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mnhyim.data.local.dao.NewsDao
import com.mnhyim.data.local.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}