package com.myfawwaz.app.mybrain.domain.use_case.notes

import com.myfawwaz.app.mybrain.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNoteFoldersUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke() = repository.getAllNoteFolders()
}