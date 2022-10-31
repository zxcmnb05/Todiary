package com.example.todiary.data.repository

import com.example.todiary.data.database.dao.DiaryDao
import com.example.todiary.data.database.entity.DiaryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DiaryRepository @Inject constructor(private val diaryDao: DiaryDao) {

    fun getAll(): Flow<List<DiaryEntity>> = diaryDao.getAll().flowOn(Dispatchers.IO).conflate()

    suspend fun getDiaryByDate(date: String) = diaryDao.getDiaryByDate(date)

    suspend fun getDiaryByIdx(idx: Int) = diaryDao.getDiaryByIdx(idx)

    suspend fun getDiaryLike() = diaryDao.getDiaryLike()

    suspend fun updateLike(like: Boolean, idx: Int) = diaryDao.updateLike(like, idx)

    suspend fun update(diary: DiaryEntity) = diaryDao.update(diary)

    suspend fun delete(diary: DiaryEntity) = diaryDao.delete(diary)

    suspend fun addDiary(diary: DiaryEntity) = diaryDao.insert(diary)
}