package com.myfawwaz.android.jawa.widget.domain.use_case.notes

import com.myfawwaz.android.jawa.widget.domain.model.Note
import com.myfawwaz.android.jawa.widget.domain.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val notesRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) = notesRepository.addNote(note)
}