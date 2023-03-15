package yt.devdroid.composenavigation.ui.note

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import yt.devdroid.composenavigation.ui.getRouteWithArgs

const val NOTE_ID_ARG = "noteId"
private const val noteRoute: String = "note?$NOTE_ID_ARG={$NOTE_ID_ARG}"
private val navArguments: List<NamedNavArgument> = listOf(
    navArgument(NOTE_ID_ARG) { type = NavType.StringType; nullable = true }
)

private fun getRouteWithNoteId(noteId: Long?) = getRouteWithArgs(noteRoute, mapOf(NOTE_ID_ARG to noteId))

fun NavGraphBuilder.addNoteScreen(onBackPress: () -> Unit) {
    composable(
        route = noteRoute,
        arguments = navArguments
    ) {
        NoteScreen(
            onBackPressed = onBackPress
        )
    }
}

fun NavController.navigateToNoteScreen(noteId: Long?) {
    navigate(route = getRouteWithNoteId(noteId = noteId))
}