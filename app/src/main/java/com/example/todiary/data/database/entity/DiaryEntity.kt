package com.example.todiary.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary_table")
data class DiaryEntity (
    @PrimaryKey(autoGenerate = true)
    var diaryIdx: Int = 0,

    @ColumnInfo(name = "post_title")
    val diaryTitle: String,

    @ColumnInfo(name = "post_content")
    val diaryContent: String,

    @ColumnInfo(name = "post_date")
    val diaryDate: String,

    @ColumnInfo(name = "post_like")
    val diaryLike: Boolean = false
)