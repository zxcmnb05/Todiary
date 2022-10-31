package com.example.todiary.ui.diary

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todiary.data.database.entity.DiaryEntity
import com.example.todiary.data.repository.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryDetailViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {

    private val _like = MutableLiveData<Boolean>(false)
    val like: LiveData<Boolean> = _like

    private val _diary: MutableState<DiaryEntity> = mutableStateOf(
        DiaryEntity(
            diaryTitle = "",
            diaryContent = "",
            diaryDate = "",
            diaryIdx = 0,
            diaryLike = false
        )
    )
    val diary: State<DiaryEntity> get() = _diary

    fun getDiary(idx: Int) = viewModelScope.launch {
        _diary.value = diaryRepository.getDiaryByIdx(idx)
        _like.value = diary.value.diaryLike
    }

    fun updateLike() = viewModelScope.launch {
        _like.value = !like.value!!
        diaryRepository.updateLike(like = like.value!!, idx = diary.value.diaryIdx)
    }

    fun deleteDiary(diary: DiaryEntity) = viewModelScope.launch {
        diaryRepository.delete(diary)
    }
}