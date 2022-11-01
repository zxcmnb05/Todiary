package com.example.todiary.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.todiary.data.database.entity.DiaryEntity
import com.example.todiary.ui.theme.Primary

@Composable
fun DiaryItem(
    selectDiary: (Int) -> Unit,
    diary: DiaryEntity
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                selectDiary(diary.diaryIdx)
            }
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Primary)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.height(IntrinsicSize.Min)
            ) {
                Text(
                    text = diary.diaryTitle,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                )
                Text(
                    text = diary.diaryDate,
                    style = MaterialTheme.typography.body2,
                    color = Color.White,
                    textAlign = TextAlign.Right
                )
            }
            Text(
                text = diary.diaryContent,
                style = MaterialTheme.typography.body2,
                color = Color.White,
                maxLines = 2
            )
        }
    }
}