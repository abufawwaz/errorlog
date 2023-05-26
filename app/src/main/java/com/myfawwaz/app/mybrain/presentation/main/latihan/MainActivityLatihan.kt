package com.myfawwaz.app.mybrain.presentation.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager.LayoutParams
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.myfawwaz.app.mybrain.domain.use_case.notes.NoteFolderDetailsScreen
import com.myfawwaz.app.mybrain.presentation.bookmarks.BookmarkDetailsScreen
import com.myfawwaz.app.mybrain.presentation.bookmarks.BookmarkSearchScreen
import com.myfawwaz.app.mybrain.presentation.bookmarks.BookmarksScreen
import com.myfawwaz.app.mybrain.presentation.calendar.CalendarEventDetailsScreen
import com.myfawwaz.app.mybrain.presentation.calendar.CalendarScreen
import com.myfawwaz.app.mybrain.presentation.diary.DiaryChartScreen
import com.myfawwaz.app.mybrain.presentation.diary.DiaryEntryDetailsScreen
import com.myfawwaz.app.mybrain.presentation.diary.DiaryScreen
import com.myfawwaz.app.mybrain.presentation.diary.DiarySearchScreen
import com.myfawwaz.app.mybrain.presentation.notes.NoteDetailsScreen
import com.myfawwaz.app.mybrain.presentation.notes.NotesScreen
import com.myfawwaz.app.mybrain.presentation.notes.NotesSearchScreen
import com.myfawwaz.app.mybrain.presentation.settings.ImportExportScreen
import com.myfawwaz.app.mybrain.presentation.tasks.TaskDetailScreen
import com.myfawwaz.app.mybrain.presentation.tasks.TasksScreen
import com.myfawwaz.app.mybrain.presentation.tasks.TasksSearchScreen

import com.myfawwaz.app.mybrain.presentation.util.CustomBottomNavigation
import com.myfawwaz.app.mybrain.presentation.util.ScreenNav

import com.myfawwaz.app.mybrain.ui.theme.MyAppTheme
import com.myfawwaz.app.mybrain.ui.theme.Rubik
import com.myfawwaz.app.mybrain.util.Constants
import com.myfawwaz.app.mybrain.util.settings.StartUpScreenSettings
import com.myfawwaz.app.mybrain.util.settings.ThemeSettings
import com.myfawwaz.app.mybrain.util.settings.toFontFamily
import com.myfawwaz.app.mybrain.util.settings.toInt
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.time.YearMonth

@OptIn(ExperimentalAnimationApi::class)
@Suppress("BlockingMethodInNonBlockingContext")
@AndroidEntryPoint
class MainActivityLatihan : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val currentScreen = mutableStateOf<ScreenNav>(ScreenNav.Home)

            MyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Greeting("Android")
                    PagerWithTabsExample()
                    //PagerWithTabs()
                    //VerticalPagerSample()

                }
            }
             }
    }
}

@Composable
fun Greeting(name: String) {
    Column(
        Modifier
            .aspectRatio(1f)
            .padding(5.dp),
        verticalArrangement = Arrangement.Top
    ) {
        val contohLambda: (String, String) -> String = {
                firstName: String, lastName: String ->
            val result = "$firstName $lastName"
            result
        }
        val result = contohLambda("TEDI ", "SUPRIADI")
        Text(text = "Hello $name! $result")
        Text(text = "Hello $name!")

    }
}

@Composable
fun CalendarHijriUi(name: String) {
    Column(
        Modifier
            .aspectRatio(1f)
            .padding(5.dp),
        verticalArrangement = Arrangement.Top
    ) {
        val contohLambda: (String, String) -> String = {
                firstName: String, lastName: String ->
            val result = "$firstName $lastName"
            result
        }
        val result = contohLambda("TEDI ", "SUPRIADI")
        val tgl1: String = "1"
        Text(text = tgl1)


    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyAppTheme {
        Greeting("Android")
    }
}


/*
@Composable
fun MainScreen() {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) } // Adjust as needed
    val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library

    val state = rememberHeatMapCalendarState(
        startMonth = startMonth,
        endMonth = startMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek,
    )
    HeatMapCalendar(
        state = state,
        dayContent = { day, _ -> Day(day) },
        weekHeader = { WeekHeader(it) },
        monthHeader = { MonthHeader(it) }
    )
}

 */