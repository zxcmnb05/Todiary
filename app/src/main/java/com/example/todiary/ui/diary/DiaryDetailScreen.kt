package com.example.todiary.ui.diary

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.example.todiary.util.LifecycleListener

@Composable
fun DiaryDetailScreen(
    diaryId: Int,
    viewModel: DiaryDetailViewModel = hiltViewModel(),
    clickUpdate: (Int) -> Unit,
    navController: NavController
) {
    viewModel.getDiary(diaryId)
    val scrollState = rememberScrollState()
    var showMenu by remember { mutableStateOf(false) }

    val like: Boolean by viewModel.like.observeAsState(false)
    val likeColor = if (like) Red else Gray

    val context = LocalContext.current

    LifecycleListener {
        when (it) {
            Lifecycle.Event.ON_RESUME -> viewModel.getDiary(diaryId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "${viewModel.diary.value.diaryTitle}") },
                backgroundColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, "back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.updateLike()
                    }) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = "like",
                            tint = likeColor
                        )
                    }
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(Icons.Filled.MoreVert, "")
                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = {
                            clickUpdate(viewModel.diary.value.diaryIdx)
                        }) { Text(text = "Update") }

                        DropdownMenuItem(onClick = {
                            Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show()
                        }) { Text(text = "Delete") }
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "${viewModel.diary.value.diaryDate}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(
                text = "${viewModel.diary.value.diaryContent}",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}