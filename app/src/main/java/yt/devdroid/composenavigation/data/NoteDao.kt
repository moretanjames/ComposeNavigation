package yt.devdroid.composenavigation.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity): Long

    @Query("SELECT * FROM notes")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
    suspend fun getNoteById(id: Long): NoteEntity
}