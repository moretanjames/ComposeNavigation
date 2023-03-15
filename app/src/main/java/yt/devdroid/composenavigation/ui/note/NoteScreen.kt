package yt.devdroid.composenavigation.ui.note

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.SimpleDateFormat
import java.util.*
import yt.devdroid.composenavigation.data.Note

@Composable
fun NoteScreen(
    onBackPressed: () -> Unit
) {

    val viewModel: NoteViewModel = viewModel()

    val note by viewModel.note.collectAsState()

    NoteScreen(
        note = note,
        updateNote = viewModel::updateNote,
        onBackPressed = onBackPressed,
        onSaveNote = { viewModel.saveNote(); onBackPressed() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    note: Note,
    updateNote: (Note) -> Unit,
    onBackPressed: () -> Unit,
    onSaveNote: () -> Unit,
    modifier: Modifier = Modifier
) {

    val formatter = remember { SimpleDateFormat("MM/dd/yy", Locale.getDefault()) }

    Scaffold(
        modifier = modifier.imePadding(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    if (!note.isSaved) {
                        IconButton(onClick = onSaveNote, enabled = note.title.isNotBlank() && note.body.isNotBlank()) {
                            Icon(imageVector = Icons.Default.Save, contentDescription = null)
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                    .padding(padding)
                    .fillMaxWidth()
                    .scrollable(rememberScrollableState { it }, orientation = Orientation.Vertical)
        ) {
            if (note.isSaved) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                ) {
                    Text(text = note.title, modifier = Modifier.weight(1f), style = MaterialTheme.typography.headlineSmall, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = formatter.format(note.date), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = note.body,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(16.dp)
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                ) {
                    TextField(
                        value = note.title,
                        onValueChange = { updateNote(note.copy(title = it)) },
                        textStyle = MaterialTheme.typography.headlineSmall,
                        singleLine = true,
                        maxLines = 1,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = { Text(text = "Untitled Note", style = MaterialTheme.typography.headlineSmall) },
                        modifier = Modifier.weight(1f),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = formatter.format(Date()), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = note.body,
                    onValueChange = { updateNote(note.copy(body = it)) },
                    textStyle = MaterialTheme.typography.bodyMedium,
                    placeholder = {
                        Text(text = "Start Your note", style = MaterialTheme.typography.bodyMedium)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(16.dp)
                )
            }
        }
    }
}