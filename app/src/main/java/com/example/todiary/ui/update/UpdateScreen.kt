package com.example.todiary.ui

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todiary.R
import com.example.todiary.ui.theme.Primary
import com.example.todiary.ui.update.UpdateViewModel

@Composable
fun UpdateScreen(
    diaryId: Int,
    navController: NavController,
    viewModel: UpdateViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    viewModel.getDiary(diaryId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        TopAppBar(viewModel, "일기수정", navController)
        ShowDatePicker(context = context, viewModel = viewModel)
        Write(viewModel = viewModel)
    }
}

@Composable
fun TopAppBar(viewModel: UpdateViewModel, text: String, navController: NavController) {
    val context = LocalContext.current

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = text, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
        Box(modifier = Modifier
            .clip(CircleShape)
            .clickable {
                if (viewModel.title.value.isNotEmpty() && viewModel.content.value.isNotEmpty() && viewModel.date.value.isNotEmpty()) {
                    viewModel.updateDiary()
                    navController.popBackStack()
                } else {
                    Toast
                        .makeText(context, "게시물을 작성해 주세요", Toast.LENGTH_SHORT)
                        .show()
                }
            }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null,
            )
        }
    }
}

@Composable
fun Write(viewModel: UpdateViewModel) {
    Spacer(modifier = Modifier.height(8.dp))
    UpdateTitle(viewModel)
    Spacer(modifier = Modifier.height(8.dp))
    UpdateContent(viewModel)
}

@Composable
fun ShowDatePicker(context: Context, viewModel: UpdateViewModel) {

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            viewModel.setDate(year, month, dayOfMonth)
        }, viewModel.year.value, viewModel.month.value, viewModel.day.value
    )
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = "${viewModel.year.value}년 ${viewModel.month.value}월 ${viewModel.day.value}일",
            fontSize = 18.sp,
            modifier = Modifier.clickable {
                datePickerDialog.show()
            })
    }
}

@Composable
fun UpdateTitle(viewModel: UpdateViewModel) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = viewModel.title.value,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Primary,
            cursorColor = Color.White,
            disabledLabelColor = Primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        onValueChange = {
            viewModel.setTitle(it)
        }, label = { Text(text = "제목을 작성해주세요", color = Color.White) })
}

@Composable
fun UpdateContent(viewModel: UpdateViewModel) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        value = viewModel.content.value,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Primary,
            cursorColor = Color.White,
            disabledLabelColor = Primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        onValueChange = {
            viewModel.setContent(it)
        }, label = { Text(text = "일기를 작성해주세요", color = Color.White) })
}