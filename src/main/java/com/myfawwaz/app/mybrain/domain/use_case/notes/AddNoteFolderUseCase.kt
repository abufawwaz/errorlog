package com.myfawwaz.app.mybrain.domain.use_case.notes

import com.myfawwaz.app.mybrain.domain.model.NoteFolder
import com.myfawwaz.app.mybrain.domain.repository.NoteRepository
import javax.inject.Inject

class AddNoteFolderUseCass @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(folder: NoteFolder) = noteRepository.insertNoteFolder(folder)
}