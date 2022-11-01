package com.example.todiary.ui.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ListOfDiaryScreen(selectDiary: (Int) -> Unit) {
    DiaryOfList(selectDiary)
}

@Composable
fun DiaryOfList(selectDiary: (Int) -> Unit, vm: ListOfDiaryViewModel = hiltViewModel()) {
    LazyColumn() {
        item {
            vm.diaryList.value.forEach {
                DiaryItem(selectDiary = { selectDiary(it) }, diary = it)
            }
        }
    }
}