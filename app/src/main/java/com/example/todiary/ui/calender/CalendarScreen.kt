package com.example.todiary.ui.calender

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todiary.ui.theme.Primary
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CalenderScreen() {
    val viewModel: CalendarViewModel = viewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {

                AndroidView(factory = { context ->
                    CalendarView(context)
                }, update = {
                    it.setOnDateChangeListener { calendarView, year, month, day ->
                        viewModel.setDate("$year - ${month + 1} - $day")
                    }
                })
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
                            for (i in 0..3) {
                                CalendarItem()
                            }
                        }
                    }
                }
            }
        }
    )

}

@Preview
@Composable
fun Preview() {
    CalenderScreen()
}