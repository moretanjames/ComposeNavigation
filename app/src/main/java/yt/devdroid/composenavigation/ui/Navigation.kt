package yt.devdroid.composenavigation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import yt.devdroid.composenavigation.ui.home.HomeScreen
import yt.devdroid.composenavigation.ui.note.NoteScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable(route = "home") {
            HomeScreen(
                onClickNote = { navController.navigate("note?noteId=${it.id}") },
                onClickCreateNote = { navController.navigate("note") }
            )
        }

        composable(
            route = "note?noteId={noteId}",
            arguments = listOf(
                navArgument("noteId") { type = NavType.StringType; nullable = true }
            )
        ) {
            NoteScreen(
                onBackPressed = navController::navigateUp
            )
        }
    }
}