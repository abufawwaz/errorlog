package com.myfawwaz.android.jawa.widget.domain.use_case.diary

import com.myfawwaz.android.jawa.widget.domain.repository.DiaryRepository
import javax.inject.Inject

class SearchEntriesUseCase @Inject constructor(
    private val repository: DiaryRepository
) {
    suspend operator fun invoke(query: String) = repository.searchEntries(query)
}