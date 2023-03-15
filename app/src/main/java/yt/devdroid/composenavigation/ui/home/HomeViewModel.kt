package yt.devdroid.composenavigation.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import yt.devdroid.composenavigation.data.NoteRepo

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val repo = NoteRepo(application)

    val notes = repo.getNotes()
}