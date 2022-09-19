package com.example.todiary.ui.calender

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.todiary.ui.theme.Primary

@Composable
fun CalenderScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        Text(
            text = "캘린더",
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun Preview() {
    CalenderScreen()
}