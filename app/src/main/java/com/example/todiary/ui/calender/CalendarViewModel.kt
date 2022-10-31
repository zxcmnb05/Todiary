package com.example.todiary.ui.calender

import android.annotation.SuppressLint
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
import java.text.SimpleDateFormat
import javax.inject.Inject

@SuppressLint("SimpleDateFormat")
@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {
    private val _date: MutableState<String> = mutableStateOf("")
    val date: State<String> get() = _date

    private val _diaryList: MutableState<Array<DiaryEntity>> = mutableStateOf(emptyArray())
    val diaryList: State<Array<DiaryEntity>> get() = _diaryList

    val year: MutableState<String> = mutableStateOf("")
    val month: MutableState<String> = mutableStateOf("")
    val day: MutableState<String> = mutableStateOf("")

    init {
        val current = System.currentTimeMillis()
        val formatter = SimpleDateFormat("yyyy-M-dd")
        val formatted = formatter.format(current)

        setDate(formatted)
        getDiaryByDate()
    }

    fun setDate(date: String) {
        _date.value = date
        val temp = date.split('-')
        year.value = temp[0]
        month.value = temp[1]
        day.value = temp[2]
    }

    fun getDiaryByDate() = viewModelScope.launch {
        Log.e("Date", date.value)
        _diaryList.value = diaryRepository.getDiaryByDate(date.value).toTypedArray()
    }
}