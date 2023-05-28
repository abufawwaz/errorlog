package com.myfawwaz.android.jawa.widget.presentation.main.components

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.myfawwaz.android.jawa.widget.presentation.calendarlib.ExampleHeatMap
import com.myfawwaz.android.jawa.widget.presentation.main.DashboardScreen
import com.myfawwaz.android.jawa.widget.presentation.main.SettingsScreen
import com.myfawwaz.android.jawa.widget.presentation.main.SpacesScreen
import com.myfawwaz.android.jawa.widget.presentation.util.Screen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    mainNavController: NavHostController,
    startUpScreen: String
) {
    NavHost(navController = navController, startDestination = startUpScreen){

        composable(Screen.DashboardScreen.route){
            DashboardScreen(mainNavController)
        }
        composable(Screen.SpacesScreen.route){
            SpacesScreen(mainNavController)
        }
        composable(Screen.SettingsScreen.route){
            SettingsScreen(mainNavController)
        }
      /*  composable(Screen.HeatMapScreen.route){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ExampleHeatMap(mainNavController)
            }
        }

       */
    }
}