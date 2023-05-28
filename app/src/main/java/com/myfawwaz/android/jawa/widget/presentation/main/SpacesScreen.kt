
package com.myfawwaz.android.jawa.widget.presentation.main

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.myfawwaz.android.jawa.widget.presentation.main.components.SpaceRegularCard
import com.myfawwaz.android.jawa.widget.presentation.main.components.SpaceWideCard
import com.myfawwaz.android.jawa.widget.presentation.util.Screen
import com.myfawwaz.android.jawa.widget.ui.theme.Green
import com.myfawwaz.android.jawa.widget.ui.theme.Orange
import com.myfawwaz.android.jawa.widget.ui.theme.Purple
import com.myfawwaz.android.jawa.widget.ui.theme.Red
import com.myfawwaz.android.jawa.widget.R
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SpacesScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.spaces),
                        style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold)
                    )
                },
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp
            )
        }
    ) {
        LazyColumn {
            item {
                Row {

                    SpaceRegularCard(
                        modifier = Modifier.weight(0.3f, fill = false),
                        title = stringResource(R.string.notes),
                        image = R.drawable.notes_img,
                        backgroundColor = Purple

                    ){
                        navController.navigate(Screen.NotesScreen.route)
                    }
                    SpaceRegularCard(
                        modifier = Modifier.weight(0.3f, fill = true),
                        title = stringResource(R.string.tasks),
                        image = R.drawable.tasks_img,
                        backgroundColor = Red
                    ){
                        navController.navigate(
                            Screen.TasksScreen.route
                        )
                    }

                    SpaceRegularCard(
                        modifier = Modifier.weight(0.3f, fill = true),
                        title = stringResource(R.string.diary),
                        image = R.drawable.diary_img,
                        backgroundColor = Green
                    ){
                        navController.navigate(Screen.DiaryScreen.route)
                    }
                    SpaceRegularCard(
                        modifier = Modifier.weight(0.3f, fill = true),
                        title = stringResource(R.string.bookmarks),
                        image = R.drawable.bookmarks_img,
                        backgroundColor = Orange
                    ){
                        navController.navigate(Screen.BookmarksScreen.route)
                    }
                }
                Row {

                    SpaceRegularCard(
                        modifier = Modifier.weight(0.3f, fill = false),
                        title = stringResource(R.string.notes),
                        image = R.drawable.notes_img,
                        backgroundColor = Purple

                    ){
                        navController.navigate(Screen.TasksScreen.route)
                    }
                    SpaceRegularCard(
                        modifier = Modifier.weight(0.3f, fill = true),
                        title = stringResource(R.string.tasks),
                        image = R.drawable.tasks_img,
                        backgroundColor = Red
                    ){
                        navController.navigate(
                            Screen.TasksScreen.route //click button
                        )
                    }
                    SpaceRegularCard(
                        modifier = Modifier.weight(0.3f, fill = true),
                        title = stringResource(R.string.diary),
                        image = R.drawable.diary_img,
                        backgroundColor = Green
                    ){
                        navController.navigate(Screen.DiaryScreen.route)
                    }
                    SpaceRegularCard(
                        modifier = Modifier.weight(0.3f, fill = true),
                        title = stringResource(R.string.bookmarks),
                        image = R.drawable.bookmarks_img,
                        backgroundColor = Orange
                    ){
                        navController.navigate(Screen.BookmarksScreen.route)
                    }
                }
            }
            item {
                SpaceWideCard(
                    title = stringResource(R.string.calendar),
                    image = R.drawable.calendar_img,
                    backgroundColor = Purple
                ){
                    navController.navigate(Screen.CalendarScreen.route)
                }
            }
            item {
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Example3Page()
            }

             */
            }

            item { Spacer(Modifier.height(60.dp)) }
        }
    }
}

@Preview
@Composable
fun SpacesScreenPreview() {
    SpacesScreen(
        navController = rememberNavController()
    )
}