package com.example.todiary.ui.update

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todiary.data.database.entity.DiaryEntity
import com.example.todiary.data.repository.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {
    private val _diary: MutableState<DiaryEntity> = mutableStateOf(
        DiaryEntity(
            diaryTitle = "",
            diaryContent = "",
            diaryDate = "",
            diaryIdx = 0
        )
    )
    val diary: State<DiaryEntity> get() = _diary

    private val _title: MutableState<String> = mutableStateOf("")
    val title: State<String> get() = _title

    private val _content: MutableState<String> = mutableStateOf("")
    val content: State<String> get() = _content

    private val _date: MutableState<String> = mutableStateOf("")
    val date: State<String> get() = _date

    val year: MutableState<Int> = mutableStateOf(0)
    val month: MutableState<Int> = mutableStateOf(0)
    val day: MutableState<Int> = mutableStateOf(0)

    fun getDiary(diaryId: Int) = viewModelScope.launch {
        _diary.value = diaryRepository.getDiaryByIdx(diaryId)

        _title.value = diary.value.diaryTitle
        _content.value = diary.value.diaryContent
        _date.value = diary.value.diaryDate

        val temp = date.value.split('-')
        year.value = temp[0].toInt()
        month.value = temp[1].toInt()
        day.value = temp[2].toInt()
    }

    fun setDate(year: Int, month: Int, day: Int) {
        this.year.value = year
        this.month.value = month
        this.day.value = day
        this._date.value = "${this.year.value}-${this.month.value+1}-${this.day.value}"
    }

    fun updateDiary() = viewModelScope.launch {
        diaryRepository.update(
            diary = DiaryEntity(
                diaryIdx = diary.value.diaryIdx,
                diaryTitle = title.value,
                diaryContent = content.value,
                diaryDate = date.value,
                diaryLike = diary.value.diaryLike
            )
        )
    }

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setContent(content: String) {
        _content.value = content
    }
}