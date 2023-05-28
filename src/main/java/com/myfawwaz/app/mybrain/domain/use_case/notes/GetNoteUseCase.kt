package com.myfawwaz.app.mybrain.domain.use_case.notes

import com.myfawwaz.app.mybrain.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    private val notesRepository: NoteRepository
) {
    suspend operator fun invoke(id: Int) = notesRepository.getNote(id)
}