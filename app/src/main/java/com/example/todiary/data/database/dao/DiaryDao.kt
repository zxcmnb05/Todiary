package com.example.todiary.data.database.dao

import androidx.room.*
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

    @Query("SELECT * FROM diary_table WHERE post_like = 1")
    suspend fun getDiaryLike(): List<DiaryEntity>

    @Query("UPDATE DIARY_TABLE SET post_like = :like WHERE diaryIdx = :idx")
    suspend fun updateLike(like: Boolean, idx: Int)

    @Update
    suspend fun update(diary: DiaryEntity)

    @Delete
    suspend fun delete(diary: DiaryEntity)

    @Insert
    suspend fun insert(diary: DiaryEntity)
}