package com.myfawwaz.android.jawa.widget.domain.use_case.notes

import com.myfawwaz.android.jawa.widget.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNoteFoldersUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke() = repository.getAllNoteFolders()
}