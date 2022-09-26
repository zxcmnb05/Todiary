package com.example.todiary.ui.write

import android.annotation.SuppressLint
import android.icu.text.CaseMap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import javax.inject.Inject

@SuppressLint("SimpleDateFormat")
@HiltViewModel
class WriteViewModel @Inject constructor() : ViewModel() {
    private val _date: MutableState<String> = mutableStateOf("")
    val date: State<String> get() = _date

    private val _title: MutableState<String> = mutableStateOf("")
    val title: State<String> get() = _title

    private val _content: MutableState<String> = mutableStateOf("")
    val content: State<String> get() = _content

    val year: MutableState<String> = mutableStateOf("")
    val month: MutableState<String> = mutableStateOf("")
    val day: MutableState<String> = mutableStateOf("")

    init {
        val current = System.currentTimeMillis()
        val formatter = SimpleDateFormat("yyyy-M-dd")
        val formatted = formatter.format(current)

        setDate(formatted)
    }

    fun setDate(date: String) {
        _date.value = date
        val temp = date.split('-')
        year.value = temp[0]
        month.value = temp[1]
        day.value = temp[2]
    }

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setContent(content: String) {
        _content.value = content
    }
}