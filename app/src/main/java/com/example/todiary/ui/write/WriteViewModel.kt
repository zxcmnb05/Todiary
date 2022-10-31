package com.example.todiary.ui.write

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todiary.data.database.entity.DiaryEntity
import com.example.todiary.data.repository.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@SuppressLint("SimpleDateFormat")
@HiltViewModel
class WriteViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {
    private val _date: MutableState<String> = mutableStateOf("")
    val date: State<String> get() = _date

    private val _title: MutableState<String> = mutableStateOf("")
    val title: State<String> get() = _title

    private val _content: MutableState<String> = mutableStateOf("")
    val content: State<String> get() = _content

    val year: MutableState<Int> = mutableStateOf(0)
    val month: MutableState<Int> = mutableStateOf(0)
    val day: MutableState<Int> = mutableStateOf(0)

    init {
        val calendar = Calendar.getInstance()
        year.value = calendar.get(Calendar.YEAR)
        month.value = calendar.get(Calendar.MONTH)
        day.value = calendar.get(Calendar.DAY_OF_MONTH)

        setDate(year.value, month.value, day.value)
    }

    fun setDate(year: Int, month: Int, day: Int) {
        this.year.value = year
        this.month.value = month
        this.day.value = day
        this._date.value = "${this.year.value}-${this.month.value+1}-${this.day.value}"
    }

    fun addDiary() = viewModelScope.launch {
        diaryRepository.addDiary(
            diary = DiaryEntity(
                diaryTitle = title.value,
                diaryContent = content.value,
                diaryDate = date.value
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