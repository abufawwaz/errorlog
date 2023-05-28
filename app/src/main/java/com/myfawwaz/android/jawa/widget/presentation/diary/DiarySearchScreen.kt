package com.myfawwaz.android.jawa.widget.presentation.diary

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.myfawwaz.android.jawa.widget.R
import com.myfawwaz.android.jawa.widget.presentation.util.Screen
import com.myfawwaz.android.jawa.widget.util.Constants

@Composable
fun DiarySearchScreen(
    navController: NavHostController,
    viewModel: DiaryViewModel = hiltViewModel()
) {

    val state = viewModel.uiState
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        var query by rememberSaveable {
            mutableStateOf("")
        }
        LaunchedEffect(query){viewModel.onEvent(DiaryEvent.SearchEntries(query))} //catatan diary
        val focusRequester = remember { FocusRequester() }
        LaunchedEffect(true){focusRequester.requestFocus()}
        OutlinedTextField(
            value = query,
            onValueChange = {query = it},
            label = { Text(stringResource(R.string.search_diary)) },
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .focusRequester(focusRequester)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(
                state.searchEntries, key = {it.id}){ entry ->
                DiaryEntryItem(
                    entry = entry,
                    onClick = {
                        navController.navigate(

                            Screen.DiaryDetailScreen.route.replace(
                                "{${Constants.DIARY_ID_ARG}}",
                                "${entry.id}"
                            )
                        )

                    }

                )
            }
        }
    }
}

@Preview(heightDp = 600)
@Composable
private fun Example6Preview() {
   // DiarySearchScreen()
}