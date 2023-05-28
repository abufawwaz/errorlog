package com.myfawwaz.android.jawa.widget.presentation.main

import android.os.Bundle
import android.view.WindowManager.LayoutParams
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.myfawwaz.android.jawa.widget.domain.use_case.notes.NoteFolderDetailsScreen
import com.myfawwaz.android.jawa.widget.presentation.bookmarks.BookmarkDetailsScreen
import com.myfawwaz.android.jawa.widget.presentation.bookmarks.BookmarkSearchScreen
import com.myfawwaz.android.jawa.widget.presentation.bookmarks.BookmarksScreen
import com.myfawwaz.android.jawa.widget.presentation.calendar.CalendarEventDetailsScreen
import com.myfawwaz.android.jawa.widget.presentation.calendar.CalendarScreen
import com.myfawwaz.android.jawa.widget.presentation.diary.DiaryChartScreen
import com.myfawwaz.android.jawa.widget.presentation.diary.DiaryEntryDetailsScreen
import com.myfawwaz.android.jawa.widget.presentation.diary.DiaryScreen
import com.myfawwaz.android.jawa.widget.presentation.diary.DiarySearchScreen
import com.myfawwaz.android.jawa.widget.presentation.notes.NoteDetailsScreen
import com.myfawwaz.android.jawa.widget.presentation.notes.NotesScreen
import com.myfawwaz.android.jawa.widget.presentation.notes.NotesSearchScreen
import com.myfawwaz.android.jawa.widget.presentation.settings.ImportExportScreen
import com.myfawwaz.android.jawa.widget.presentation.tasks.TaskDetailScreen
import com.myfawwaz.android.jawa.widget.presentation.tasks.TasksScreen
import com.myfawwaz.android.jawa.widget.presentation.tasks.TasksSearchScreen
import com.myfawwaz.android.jawa.widget.presentation.util.Screen
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

@Suppress("BlockingMethodInNonBlockingContext")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val themeMode = viewModel.themeMode.collectAsState(initial = ThemeSettings.AUTO.value)
            val font = viewModel.font.collectAsState(initial = Rubik.toInt())
            val blockScreenshots = viewModel.blockScreenshots.collectAsState(initial = false)
            var startUpScreenSettings by remember { mutableStateOf(StartUpScreenSettings.SPACES.value) }
            val systemUiController = rememberSystemUiController()
            LaunchedEffect(true) {
                runBlocking {
                    startUpScreenSettings = viewModel.defaultStartUpScreen.first()
                }
            }
            LaunchedEffect(blockScreenshots.value) {
                if (blockScreenshots.value) {
                    window.setFlags(
                        LayoutParams.FLAG_SECURE,
                        LayoutParams.FLAG_SECURE
                    )
                } else
                    window.clearFlags(LayoutParams.FLAG_SECURE)
            }
            val startUpScreen =
                if (startUpScreenSettings == StartUpScreenSettings.SPACES.value)
                    Screen.SpacesScreen.route else Screen.DashboardScreen.route
            val isDarkMode = when (themeMode.value) {
                ThemeSettings.DARK.value -> true
                ThemeSettings.LIGHT.value -> false
                else -> isSystemInDarkTheme()
            }
            SideEffect {
                systemUiController.setSystemBarsColor(
                    if (isDarkMode) Color.Black else Color.White,
                    darkIcons = !isDarkMode
                )
            }
            MyAppTheme(darkTheme = isDarkMode, fontFamily = font.value.toFontFamily()) {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        startDestination = Screen.Main.route,
                        navController = navController
                    ) {
                        composable(Screen.Main.route) {
                            MainScreen(
                                startUpScreen = startUpScreen,
                                mainNavController = navController
                            )
                        }
                        composable(
                            Screen.TasksScreen.route,
                            arguments = listOf(navArgument(Constants.ADD_TASK_ARG) {
                                type = NavType.BoolType
                                defaultValue = false
                            }),
                            deepLinks =
                            listOf(
                                navDeepLink {
                                    uriPattern =
                                        "${Constants.TASKS_SCREEN_URI}/{${Constants.ADD_TASK_ARG}}"
                                }
                            )
                        ) {
                            TasksScreen(
                                navController = navController,
                                addTask = it.arguments?.getBoolean(Constants.ADD_TASK_ARG)
                                    ?: false
                            )
                        }
                        composable(
                            Screen.TaskDetailScreen.route,
                            arguments = listOf(navArgument(Constants.TASK_ID_ARG) {
                                type = NavType.IntType
                            }),
                            deepLinks =
                            listOf(
                                navDeepLink {
                                    uriPattern =
                                        "${Constants.TASK_DETAILS_URI}/{${Constants.TASK_ID_ARG}}"
                                }
                            )
                        ) {
                            TaskDetailScreen(
                                navController = navController,
                                it.arguments?.getInt(Constants.TASK_ID_ARG)!!
                            )
                        }
                        composable(Screen.TaskSearchScreen.route) {
                            TasksSearchScreen(navController = navController)
                        }
                        composable(
                            Screen.NotesScreen.route
                        ) {
                            NotesScreen(navController = navController)
                        }
                        composable(
                            Screen.NoteDetailsScreen.route,
                            arguments = listOf(navArgument(Constants.NOTE_ID_ARG) {
                                type = NavType.IntType
                            },
                                navArgument(Constants.FOLDER_ID) {
                                    type = NavType.IntType
                                }
                            ),
                        ) {
                            NoteDetailsScreen(
                                navController,
                                it.arguments?.getInt(Constants.NOTE_ID_ARG) ?: -1,
                                it.arguments?.getInt(Constants.FOLDER_ID) ?: -1
                            )
                        }
                        composable(Screen.NoteSearchScreen.route) {
                            NotesSearchScreen(navController = navController)
                        }
                        composable(Screen.DiaryScreen.route) {
                            DiaryScreen(navController = navController)
                        }
                        composable(Screen.DiaryChartScreen.route) {
                            DiaryChartScreen()
                        }
                        composable(Screen.DiarySearchScreen.route) {
                            DiarySearchScreen(navController = navController)
                        }
                        composable(
                            Screen.DiaryDetailScreen.route,
                            arguments = listOf(navArgument(Constants.DIARY_ID_ARG) {
                                type = NavType.IntType
                            })
                        ) {
                            DiaryEntryDetailsScreen(
                                navController = navController,
                                it.arguments?.getInt(Constants.DIARY_ID_ARG)!!
                            )
                        }
                        composable(Screen.BookmarksScreen.route) {
                            BookmarksScreen(navController = navController)
                        }
                        composable(
                            Screen.BookmarkDetailScreen.route,
                            arguments = listOf(navArgument(Constants.BOOKMARK_ID_ARG) {
                                type = NavType.IntType
                            })
                        ) {
                            BookmarkDetailsScreen(
                                navController = navController,
                                it.arguments?.getInt(Constants.BOOKMARK_ID_ARG)!!
                            )
                        }
                        composable(Screen.BookmarkSearchScreen.route) {
                            BookmarkSearchScreen(navController = navController)
                        }
                        composable(
                            Screen.CalendarScreen.route,
                            deepLinks = listOf(
                                navDeepLink {
                                    uriPattern = Constants.CALENDAR_SCREEN_URI
                                }
                            )
                        ) {
                            CalendarScreen(navController = navController)
                        }
                        composable(
                            Screen.CalendarEventDetailsScreen.route,
                            arguments = listOf(navArgument(Constants.CALENDAR_EVENT_ARG) {
                                type = NavType.StringType
                            }),
                            deepLinks = listOf(
                                navDeepLink {
                                    uriPattern = "${Constants.CALENDAR_DETAILS_SCREEN_URI}/{${Constants.CALENDAR_EVENT_ARG}}"
                                }
                            )
                        ) {
                            CalendarEventDetailsScreen(
                                navController = navController,
                                eventJson = it.arguments?.getString(Constants.CALENDAR_EVENT_ARG) ?: ""
                            )
                        }
                        composable(
                            Screen.NoteFolderDetailsScreen.route,
                            arguments = listOf(navArgument(Constants.FOLDER_ID) {
                                type = NavType.IntType
                            })
                        ) {
                            NoteFolderDetailsScreen(
                                navController = navController,
                                it.arguments?.getInt(Constants.FOLDER_ID) ?: -1
                            )
                        }
                        composable(Screen.ImportExportScreen.route) {
                            ImportExportScreen()
                        }
                    }
                }
            }
        }
    }
}