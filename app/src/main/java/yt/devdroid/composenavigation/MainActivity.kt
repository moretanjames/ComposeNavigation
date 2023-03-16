package yt.devdroid.composenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import yt.devdroid.composenavigation.ui.home.HomeScreen
import yt.devdroid.composenavigation.ui.note.NoteScreen
import yt.devdroid.composenavigation.ui.note.NoteViewModel
import yt.devdroid.composenavigation.ui.theme.ComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ComposeNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    var screen by rememberSaveable { mutableStateOf(Screen.HOME) }

                    val viewModel: NoteViewModel = viewModel()

                    val note by viewModel.note.collectAsState()

                    when (screen) {
                        Screen.HOME -> {
                            HomeScreen(onClickNote = { viewModel.setNote(it); screen = Screen.NOTE }, onClickCreateNote = { viewModel.setNote(null); screen = Screen.NOTE })
                        }
                        Screen.NOTE -> {
                            NoteScreen(
                                note = note,
                                updateNote = { viewModel.updateNote(it) },
                                onBackPressed = { screen = Screen.HOME },
                                onSaveNote = { viewModel.saveNote(); screen = Screen.HOME }
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class Screen {
    HOME, NOTE
}

