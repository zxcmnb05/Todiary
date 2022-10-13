package com.example.todiary.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todiary.R
import com.example.todiary.ui.calender.CalenderScreen
import com.example.todiary.ui.diary.DiaryDetailScreen
import com.example.todiary.ui.profile.ProfileScreen
import com.example.todiary.ui.theme.LightGray
import com.example.todiary.ui.theme.Primary
import com.example.todiary.ui.write.WriteScreen

@Composable
fun TodiaryApp() {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNav(navController = navController) }) {
        Box(modifier = Modifier.padding(it)) {
            NavigationGraph(navController = navController)
        }
    }
}

@Composable
fun BottomNav(navController: NavController) {
    val items = listOf<NavItem>(
        NavItem.Calender,
        NavItem.Write,
        NavItem.Profile
    )
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = LightGray
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.title),
                        modifier = Modifier
                            .height(24.dp)
                            .width(24.dp)
                    )
                },
                label = { Text(text = stringResource(id = item.title), fontSize = 10.sp) },
                selectedContentColor = Primary,
                unselectedContentColor = LightGray,
                selected = currentRoute == item.route,
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestDisplayName.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavItem.Calender.route) {
        composable(NavItem.Calender.route) {
            CalenderScreen(
                selectDiary = {
                    navController.navigate(NavItem.Diary.route)
                }
            )
        }
        composable(NavItem.Write.route) {
            WriteScreen()
        }
        composable(NavItem.Profile.route) {
            ProfileScreen()
        }
        composable(NavItem.Diary.route, arguments = listOf(
            navArgument("diaryId") { type = NavType.IntType }
        )){
            val diaryId = it.arguments?.getInt("diaryId") ?: return@composable
            DiaryDetailScreen(diaryId = diaryId)
        }
    }
}

sealed class NavItem(
    val title: Int, val icon: Int, val route: String
) {
    object Calender : NavItem(R.string.text_calendar, R.drawable.ic_calendar, "CALENDER")
    object Write : NavItem(R.string.text_write, R.drawable.ic_create, "WRITE")
    object Profile : NavItem(R.string.text_profile, R.drawable.ic_person, "PROFILE")
    object Diary : NavItem(R.string.text_diary, 0, "Diary")
}

@Preview
@Composable
fun Preview() {
    TodiaryApp()
}