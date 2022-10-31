package com.example.todiary.ui.calender

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.todiary.data.database.entity.DiaryEntity
import com.example.todiary.ui.theme.Primary

@Composable
fun CalendarItem(diary: DiaryEntity, selectDiary: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { selectDiary(diary.diaryIdx) }
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Primary)
    ) {
        Column(
            Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ) {

            CalendarItemTitle(diary)
            CalendarItemContent(diary)
        }
    }
}

@Composable
fun CalendarItemTitle(diary: DiaryEntity) {
    Text(text = diary.diaryTitle, style = MaterialTheme.typography.subtitle1, color = Color.White)
}

@Composable
fun CalendarItemContent(diary: DiaryEntity) {
    Text(text = diary.diaryContent, style = MaterialTheme.typography.body2)
}