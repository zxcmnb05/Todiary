package com.example.todiary.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todiary.data.database.dao.DiaryDao
import com.example.todiary.data.database.entity.DiaryEntity

@Database(entities = [DiaryEntity::class], version = 1, exportSchema = false)
abstract class DiaryDatabase: RoomDatabase() {
    abstract fun diaryDao(): DiaryDao
}