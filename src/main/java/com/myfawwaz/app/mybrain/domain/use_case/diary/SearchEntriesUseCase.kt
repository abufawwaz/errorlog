package com.myfawwaz.app.mybrain.domain.use_case.diary

import com.myfawwaz.app.mybrain.domain.repository.DiaryRepository
import javax.inject.Inject

class SearchEntriesUseCase @Inject constructor(
    private val repository: DiaryRepository
) {
    suspend operator fun invoke(query: String) = repository.searchEntries(query)
}