package com.example.todiary.ui.calender

import android.widget.CalendarView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import com.example.todiary.util.LifecycleListener

@Composable
fun CalenderScreen(
    selectDiary: (Int) -> Unit,
    viewModel: CalendarViewModel,
) {
    LifecycleListener {
        when (it) {
            Lifecycle.Event.ON_RESUME -> viewModel.getDiaryByDate()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Calendar(viewModel = viewModel)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "${viewModel.year.value}년 ${viewModel.month.value}월 ${viewModel.day.value}일 일기",
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
                    )
                    LazyColumn() {
                        item {
                            viewModel.diaryList.value.forEach {
                                CalendarItem(selectDiary = selectDiary, diary = it)
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun Calendar(viewModel: CalendarViewModel) {
    AndroidView(factory = { context ->
        CalendarView(context)
    }, update = {
        it.setOnDateChangeListener { calendarView, year, month, day ->
            viewModel.setDate("$year-${month + 1}-$day")
            viewModel.getDiaryByDate()
        }
    })
}