package com.example.todiary.ui.write

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.todiary.ui.theme.LightGray
import com.example.todiary.ui.theme.Primary

@Composable
fun WriteScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGray)
    ) {
        Text(
            text = "작성하기",
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center,
            color = Primary,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}