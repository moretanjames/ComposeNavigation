package yt.devdroid.composenavigation.data

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.flow.map

class NoteRepo(context: Context) {
    private val dao = Room.databaseBuilder(context, Database::class.java, "notes-db").build().noteDao()

    fun getNotes() = dao.getNotes().map { notes -> notes.map { it.toNote() } }

    suspend fun insertNote(note: Note): Boolean {
        return runCatching {
            dao.insertNote(note.toEntity())
        }.getOrNull() != null
    }

    suspend fun getNoteById(id: Long): Note? {
        return runCatching { dao.getNoteById(id) }.getOrNull()?.toNote()
    }
}