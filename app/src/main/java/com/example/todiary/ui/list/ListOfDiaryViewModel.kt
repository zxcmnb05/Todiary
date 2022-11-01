package com.example.todiary.ui.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.todiary.data.database.entity.DiaryEntity
import com.example.todiary.data.repository.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListOfDiaryViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {
    private val _diaryList: MutableState<Array<DiaryEntity>> = mutableStateOf(emptyArray())
    val diaryList: State<Array<DiaryEntity>> get() = _diaryList


}