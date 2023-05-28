package com.myfawwaz.android.jawa.widget.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenNav(
    val id:String,
    val title:String,
    val icon: ImageVector,
    val route: String,
){
    object Home: ScreenNav("hom", "Home", Icons.Outlined.Home, "main_screen")
    object Search: ScreenNav("search", "Sear", Icons.Outlined.Search, "spaces_screen")
    object Profile: ScreenNav("profile", "Profile", Icons.Outlined.Person, "dashboard_screen")
    object Settings: ScreenNav("settings", "Settings", Icons.Outlined.Settings, "settings_screen")

    object ItemsNav{
        val list = listOf(
            Home, Search, Profile, Settings
        )
    }
}
