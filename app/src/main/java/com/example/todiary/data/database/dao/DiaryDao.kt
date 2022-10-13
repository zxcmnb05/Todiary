package com.example.todiary.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.todiary.data.database.entity.DiaryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {

    @Query("SELECT * FROM diary_table")
    fun getAll(): Flow<List<DiaryEntity>>

    @Query("SELECT * FROM diary_table WHERE post_date = :date")
    suspend fun getDiaryByDate(date: String): List<DiaryEntity>

    @Query("SELECT * FROM diary_table WHERE diaryIdx = :idx")
    suspend fun getDiaryByIdx(idx: Int): DiaryEntity

    @Delete
    suspend fun delete(post: DiaryEntity)

    @Insert
    suspend fun insert(post: DiaryEntity)
}