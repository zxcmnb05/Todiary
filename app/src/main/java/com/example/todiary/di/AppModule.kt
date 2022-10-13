package com.example.todiary.di

import android.content.Context
import androidx.room.Room
import com.example.todiary.data.database.DiaryDatabase
import com.example.todiary.data.database.dao.DiaryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): DiaryDatabase = Room
        .databaseBuilder(context, DiaryDatabase::class.java, "diary_db")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideDiaryDao(diaryDatabase: DiaryDatabase): DiaryDao = diaryDatabase.diaryDao()
}