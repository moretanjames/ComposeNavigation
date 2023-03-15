package yt.devdroid.composenavigation.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}