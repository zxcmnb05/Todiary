package com.example.todiary.ui.calender

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor() : ViewModel(){
    private val _date: MutableState<String> = mutableStateOf("")
    val date: State<String> get() = _date

    val year: MutableState<String> = mutableStateOf("")
    val month: MutableState<String> = mutableStateOf("")
    val day: MutableState<String> = mutableStateOf("")

    init {
        val current = System.currentTimeMillis()
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val formatted = formatter.format(current)

        setDate(formatted)
    }

    fun setDate(date: String) {
        _date.value = date
        var temp = date.split('-')
        year.value = temp[0]
        month.value = temp[1]
        day.value = temp[2]
    }
}