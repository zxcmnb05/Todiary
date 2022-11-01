package com.example.todiary.ui.profile

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todiary.ui.theme.LightGray

@Composable
fun ProfileScreen(
    clickList: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        TopAppBar()
        Spacer(modifier = Modifier.height(16.dp))
        MyDiary(clickList, viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        Setting()
        Spacer(modifier = Modifier.height(16.dp))
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .padding()
        )
        DarkMode()
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .padding()
        )
    }
}

@Composable
fun TopAppBar() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "더보기", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun MyDiary(clickList: () -> Unit, viewModel: ProfileViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(horizontal = 40.dp)
            .clip(RoundedCornerShape(2.dp))
            .border(width = 0.5.dp, color = Color.Black, shape = RoundedCornerShape(10.dp)),
        Arrangement.SpaceEvenly
    ) {
        WrittenDiary(clickList, viewModel)
        Divider(
            color = LightGray, modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .padding(vertical = 24.dp)
        )
        BookMarkDiary(clickList, viewModel)
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun WrittenDiary(clickList: () -> Unit, viewModel: ProfileViewModel) {
    val size = if (viewModel.postList.value.isNotEmpty()) viewModel.postList.value.size else 0

    Column(
        modifier = Modifier
            .padding(24.dp)
            .clickable {
                clickList()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "작성한 일기", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        Text(text = "$size", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BookMarkDiary(clickList: () -> Unit, viewModel: ProfileViewModel) {
    val size =
        if (viewModel.likeDiaryList.value.isNotEmpty()) viewModel.likeDiaryList.value.size else 0

    Column(
        modifier = Modifier
            .padding(24.dp)
            .clickable {
                clickList()
            }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "즐겨찾기", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        Text(text = "$size", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun Setting() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "설정", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun DarkMode() {
    val context = LocalContext.current

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Min)
        .clickable {
            Toast
                .makeText(context, "추후 추가 예정", Toast.LENGTH_SHORT)
                .show()
        }) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "다크모드",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.KeyboardArrowRight, contentDescription = null)
        }
    }
}