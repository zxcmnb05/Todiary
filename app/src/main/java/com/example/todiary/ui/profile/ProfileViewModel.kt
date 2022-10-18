package com.example.todiary.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todiary.data.database.entity.DiaryEntity
import com.example.todiary.data.repository.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {
    private val _diaryList = MutableStateFlow<List<DiaryEntity>>(emptyList())
    val postList = _diaryList.asStateFlow()

    private val _bookmarkList = MutableStateFlow<List<DiaryEntity>>(emptyList())
    val bookmarkList = _bookmarkList.asStateFlow()

    fun getDiaryList() {
        viewModelScope.launch(Dispatchers.IO) {
            diaryRepository.getAll().distinctUntilChanged().collect() { it ->
                _diaryList.value = it
                _bookmarkList.value = it.filter { it.postLike == 1 }
            }
        }
    }
}