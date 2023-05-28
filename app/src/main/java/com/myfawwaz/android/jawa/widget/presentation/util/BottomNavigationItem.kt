package com.myfawwaz.android.jawa.widget.presentation.util

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myfawwaz.app.mybrain.R


sealed class BottomNavItem(
    val title: Int, val icon: Int, val iconSelected: Int, val route: String
    ){
    object Dashboard : BottomNavItem(R.string.dashboard, R.drawable.ic_home, R.drawable.ic_home_filled, Screen.DashboardScreen.route)
    object Spaces : BottomNavItem(R.string.spaces, R.drawable.ic_spaces, R.drawable.ic_spaces_filled, Screen.SpacesScreen.route)
    object Settings: BottomNavItem(R.string.settings, R.drawable.ic_settings, R.drawable.ic_settings_filled, Screen.SettingsScreen.route)

}