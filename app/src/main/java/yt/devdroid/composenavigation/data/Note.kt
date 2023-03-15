package yt.devdroid.composenavigation.data

import java.util.*

data class Note(
    val id: Long = -1,
    val title: String = "",
    val body: String = "",
    val date: Date = Date()
) {
    fun toEntity() = NoteEntity(id = id, title = title, body = body, date = date.time)

    val isSaved get() = id > -1
}