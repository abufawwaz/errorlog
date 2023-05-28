@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.myfawwaz.android.jawa.widget.presentation.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.myfawwaz.android.jawa.widget.presentation.util.BottomNavItem
import com.myfawwaz.android.jawa.widget.ui.MaxTextLenght

@ExperimentalAnimationApi
@Composable
fun MainBottomBar(
    navController: NavHostController,
    items: List<BottomNavItem>,
) {
    BottomNavigation (backgroundColor = MaterialTheme.colors.background) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach {
            val background= if(currentDestination?.route == it.route) MaterialTheme.colors.primary.copy(alpha = 0.1f) else Color.Transparent
            val contentColor = if(currentDestination?.route == it.route) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground

            BottomNavigationItem(
                icon = {
                    Box(

                        modifier = Modifier
                            .clip(CircleShape)
                            .background(background)

                    ){
                        Row(
                            modifier = Modifier
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Icon(
                                if (currentDestination?.route == it.route)
                                    painterResource(it.iconSelected)
                                else
                                    painterResource(it.icon),
                                contentDescription = stringResource(it.title),
                                tint = contentColor
                            )
                            var selsetedIt = false
                            if(currentDestination?.route == it.route) selsetedIt = true
                            AnimatedVisibility(visible = selsetedIt) {
                                Text(
                                    text = ""+ MaxTextLenght.MaxTextLenghtText(stringResource(it.title),7,".."),
                                    color = contentColor
                                )
                            }

                        }
                    }//box

                       },
                selected = currentDestination?.route == it.route,
                onClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = true


            )
        }
    }
}



@ExperimentalAnimationApi
@Composable
@Preview
fun Prev12(){
    BottomNavigation{

    }
}