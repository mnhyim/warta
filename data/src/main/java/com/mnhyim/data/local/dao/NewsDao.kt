package com.mnhyim.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mnhyim.data.local.entity.ArticleEntity
import com.mnhyim.domain.model.articles.ArticleModel

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAll(): List<ArticleEntity>

    @Insert
    fun insert(news: ArticleEntity)

    @Delete
    fun delete(news: ArticleEntity)
}