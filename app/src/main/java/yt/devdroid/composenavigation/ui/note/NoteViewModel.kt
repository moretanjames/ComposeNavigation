package yt.devdroid.composenavigation.ui.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import yt.devdroid.composenavigation.data.Note
import yt.devdroid.composenavigation.data.NoteRepo

class NoteViewModel(application: Application, savedStateHandle: SavedStateHandle) : AndroidViewModel(application) {

    private val repo = NoteRepo(application)

    private val existingNoteId = savedStateHandle.getStateFlow<String?>("noteId", null)

    private val existingNote = existingNoteId.map { it?.let { runCatching { repo.getNoteById(it.toLong()) }.getOrNull() } }

    private val createdNote = MutableStateFlow(Note())

    val note = combine(existingNote, createdNote) { existing, created ->
        existing ?: created
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), Note())

    fun saveNote() = viewModelScope.launch(Dispatchers.IO) {
        repo.insertNote(note.value)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        createdNote.emit(note)
    }
}