package yt.devdroid.composenavigation.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val title: String,
    val body: String,
    val date: Long
) {
    fun toNote() = Note(id = id, title = title, body = body, date = Date(date))
}