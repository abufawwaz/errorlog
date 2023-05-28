package com.myfawwaz.android.jawa.widget.presentation.main.latihan

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myfawwaz.android.jawa.widget.presentation.calendarlib.CalViewContent
import com.myfawwaz.android.jawa.widget.presentation.main.MainViewModel

import com.myfawwaz.android.jawa.widget.presentation.util.ScreenNav

import com.myfawwaz.android.jawa.widget.ui.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalAnimationApi::class)
@Suppress("BlockingMethodInNonBlockingContext")
@AndroidEntryPoint
class MainActivityLatihan : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
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
                    //PagerWithTabsExample()
                    //ExampleHeatMap(mainNavController)
                    //PagerWithTabs()
                    //VerticalPagerSample()
                    CalViewContent()

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