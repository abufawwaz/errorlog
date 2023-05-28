package com.myfawwaz.android.jawa.widget.presentation.notes

import com.myfawwaz.android.jawa.widget.domain.model.Note
import com.myfawwaz.android.jawa.widget.domain.model.NoteFolder
import com.myfawwaz.android.jawa.widget.util.settings.ItemView
import com.myfawwaz.android.jawa.widget.util.settings.Order

sealed class NoteEvent {
    data class GetNote(val noteId: Int) : NoteEvent()
    data class AddNote(val note: Note) : NoteEvent()
    data class SearchNotes(val query: String) : NoteEvent()
    data class UpdateOrder(val order: Order) : NoteEvent()
    data class UpdateView(val view: ItemView) : NoteEvent()
    data class UpdateNote(val note: Note) : NoteEvent()
    data class DeleteNote(val note: Note) : NoteEvent()
    object PinNote : NoteEvent()
    object ToggleReadingMode : NoteEvent()
    object ErrorDisplayed: NoteEvent()
    data class CreateFolder(val folder: NoteFolder): NoteEvent()
    data class DeleteFolder(val folder: NoteFolder): NoteEvent()
    data class UpdateFolder(val folder: NoteFolder): NoteEvent()
    data class GetFolderNotes(val id: Int): NoteEvent()
    data class GetFolder(val id: Int): NoteEvent()
}