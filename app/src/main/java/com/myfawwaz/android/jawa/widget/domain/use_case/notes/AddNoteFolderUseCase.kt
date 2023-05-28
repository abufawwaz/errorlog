package com.myfawwaz.android.jawa.widget.domain.use_case.notes

import com.myfawwaz.android.jawa.widget.domain.model.NoteFolder
import com.myfawwaz.android.jawa.widget.domain.repository.NoteRepository
import javax.inject.Inject

class AddNoteFolderUseCass @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(folder: NoteFolder) = noteRepository.insertNoteFolder(folder)
}