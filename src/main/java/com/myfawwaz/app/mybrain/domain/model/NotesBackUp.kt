package com.myfawwaz.app.mybrain.domain.model

data class NotesBackUp(
    val notes: List<Note>,
    val folders: List<NoteFolder>
)
