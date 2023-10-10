package com.mnhyim.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mnhyim.data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAll(): Flow<List<ArticleEntity>>

    @Insert
    fun insert(news: ArticleEntity)

    @Delete
    fun delete(news: ArticleEntity)
}