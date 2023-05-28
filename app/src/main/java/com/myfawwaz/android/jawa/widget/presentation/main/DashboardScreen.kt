package com.myfawwaz.android.jawa.widget.presentation.main

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.myfawwaz.android.jawa.widget.R
import com.myfawwaz.android.jawa.widget.domain.model.CalendarEvent
import com.myfawwaz.android.jawa.widget.presentation.calendar.CalendarDashboardWidget
import com.myfawwaz.android.jawa.widget.presentation.calendarlib.HeatMapCal
import com.myfawwaz.android.jawa.widget.presentation.diary.MoodCircularBar
import com.myfawwaz.android.jawa.widget.presentation.main.components.SpaceWideCard
import com.myfawwaz.android.jawa.widget.presentation.tasks.TasksDashboardWidget
import com.myfawwaz.android.jawa.widget.presentation.util.Screen
import com.myfawwaz.android.jawa.widget.ui.theme.Purple
import com.myfawwaz.android.jawa.widget.util.Constants
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashboardScreen(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.dashboard),
                        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
                    )
                },
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp
            )
        }
    ) {
        LaunchedEffect(true) { viewModel.onDashboardEvent(DashboardEvent.InitAll) }
        LazyColumn {
            item {
                CalendarDashboardWidget(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.5f),
                    events = viewModel.uiState.dashBoardEvents,
                    onClick = {
                        navController.navigate(
                            Screen.CalendarScreen.route
                        )
                    },
                    onPermission = {
                        viewModel.onDashboardEvent(DashboardEvent.ReadPermissionChanged(it))
                    },
                    onAddEventClicked = {
                        navController.navigate(
                            Screen.CalendarEventDetailsScreen.route.replace(
                                "{${Constants.CALENDAR_EVENT_ARG}}",
                                " "
                            )
                        )
                    },
                    onEventClicked = {
                        val eventJson = Gson().toJson(it, CalendarEvent::class.java)
                        // encoding the string to avoid crashes when the event contains fields that equals a URL
                        val encodedJson = URLEncoder.encode(eventJson, StandardCharsets.UTF_8.toString())
                        navController.navigate(
                            Screen.CalendarEventDetailsScreen.route.replace(
                                "{${Constants.CALENDAR_EVENT_ARG}}",
                                encodedJson
                            )
                        )
                    }
                )
            }
            item {
                TasksDashboardWidget(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.5f),
                    tasks = viewModel.uiState.dashBoardTasks,
                    onCheck = {
                        viewModel.onDashboardEvent(DashboardEvent.UpdateTask(it))
                    },
                    onTaskClick = {
                        navController.navigate(
                            Screen.TaskDetailScreen.route
                                .replace(
                                    "{${Constants.TASK_ID_ARG}}",
                                    it.id.toString()
                                )
                        )
                    },
                    onAddClick = {
                        navController.navigate(
                            Screen.TasksScreen
                                .route
                                .replace(
                                    "{${Constants.ADD_TASK_ARG}}",
                                    "true"
                                )
                        )
                    },
                    onClick = {
                        navController.navigate(
                            Screen.TasksScreen.route
                        )
                    }
                )
            }
            item {
                Row {
                    TasksSummaryCard(
                        modifier = Modifier.weight(1f, fill = true),
                        tasks = viewModel.uiState.summaryTasks
                    )
                    MoodCircularBar(
                        entries = viewModel.uiState.dashBoardEntries,
                        showPercentage = false,
                        modifier = Modifier.weight(1f, fill = true),
                        onClick = {
                            navController.navigate(
                                Screen.DiaryChartScreen.route
                            )
                        }
                    )

                }
            }
            item {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    HeatMapCal() // menampilkan map
                }
                /*
                SpaceWideCard(
                    title = stringResource(R.string.calendar),
                    image = R.drawable.calendar_img,
                    backgroundColor = Purple
                ){
                    navController.navigate(route="Contoh")

                }

                 */
            }
            item { Spacer(Modifier.height(65.dp)) }
        }
    }
}

@ExperimentalAnimationApi
@Composable
@Preview
fun PrevHome(){
    SpaceWideCard(
        title = stringResource(R.string.calendar),
        image = R.drawable.calendar_img,
        backgroundColor = Purple
    ){
          }
    HeatMapCal()

}