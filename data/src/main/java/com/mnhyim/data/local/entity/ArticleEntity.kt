package com.mnhyim.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "date") val date: Long?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "source") val source: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "url") val url: String?,
)