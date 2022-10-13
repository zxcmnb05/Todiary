package com.example.todiary.ui.diary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DiaryDetailScreen(diaryId: Int) {
    val viewModel: DiaryDetailViewModel = viewModel()

    Column(modifier = Modifier.fillMaxSize()) {

    }
}