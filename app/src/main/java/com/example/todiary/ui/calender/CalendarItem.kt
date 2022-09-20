package com.example.todiary.ui.calender

import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todiary.ui.theme.Primary

@Composable
fun CalendarItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Primary)
    ) {
        Column(
            Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ) {

            CalendarItemTitle()
            CalendarItemContent()
        }
    }
}

@Composable
fun CalendarItemTitle() {
    Text(text = "제목입니다.", style = MaterialTheme.typography.subtitle1, color = Color.White)
}

@Composable
fun CalendarItemContent() {
    Text(text = "콘텐츠 입니다.", style = MaterialTheme.typography.body2)
}