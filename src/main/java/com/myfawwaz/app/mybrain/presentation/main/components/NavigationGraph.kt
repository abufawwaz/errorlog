package com.myfawwaz.app.mybrain.presentation.main.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.myfawwaz.app.mybrain.presentation.main.DashboardScreen
import com.myfawwaz.app.mybrain.presentation.main.SettingsScreen
import com.myfawwaz.app.mybrain.presentation.main.SpacesScreen
import com.myfawwaz.app.mybrain.presentation.util.Screen

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

    }
}