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

    private val _likeDiaryList = MutableStateFlow<List<DiaryEntity>>(emptyList())
    val likeDiaryList = _likeDiaryList.asStateFlow()

    init {
        getDiaryList()
        getLikeDiaryList()
    }

    private fun getDiaryList() {
        viewModelScope.launch(Dispatchers.IO) {
            diaryRepository.getAll().collect {
                _diaryList.value = it
            }
        }
    }

    private fun getLikeDiaryList() {
        viewModelScope.launch(Dispatchers.IO) {
            _likeDiaryList.value = diaryRepository.getDiaryLike()
        }
    }
}