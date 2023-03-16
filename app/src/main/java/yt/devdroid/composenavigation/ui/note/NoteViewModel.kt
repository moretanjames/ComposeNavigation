package yt.devdroid.composenavigation.ui.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import yt.devdroid.composenavigation.data.Note
import yt.devdroid.composenavigation.data.NoteRepo

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = NoteRepo(application)

    private val existingNote = MutableStateFlow<Note?>(null)

    private val createdNote = MutableStateFlow(Note(id = -1))

    val note = combine(existingNote, createdNote) { existing, created ->
        existing ?: created
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), Note(id = -1))

    fun setNote(note: Note?) = viewModelScope.launch {
        existingNote.emit(note)
    }

    fun saveNote() = viewModelScope.launch(Dispatchers.IO) {
        repo.insertNote(note.value)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        createdNote.emit(note)
    }
}