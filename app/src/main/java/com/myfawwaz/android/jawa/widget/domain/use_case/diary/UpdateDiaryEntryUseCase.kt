package com.myfawwaz.android.jawa.widget.domain.use_case.diary

import com.myfawwaz.android.jawa.widget.domain.model.DiaryEntry
import com.myfawwaz.android.jawa.widget.domain.repository.DiaryRepository
import javax.inject.Inject

class UpdateDiaryEntryUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository
) {
    suspend operator fun invoke(entry: DiaryEntry) = diaryRepository.updateEntry(entry)
}