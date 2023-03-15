package yt.devdroid.composenavigation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import yt.devdroid.composenavigation.ui.home.addHomeScreen
import yt.devdroid.composenavigation.ui.home.homeRoute
import yt.devdroid.composenavigation.ui.note.addNoteScreen
import yt.devdroid.composenavigation.ui.note.navigateToNoteScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = homeRoute
    ) {
        addHomeScreen(
            onClickNote = { navController.navigateToNoteScreen(it.id) },
            onClickCreateNote = { navController.navigateToNoteScreen(null) }
        )

        addNoteScreen(
            onBackPress = navController::navigateUp
        )
    }
}