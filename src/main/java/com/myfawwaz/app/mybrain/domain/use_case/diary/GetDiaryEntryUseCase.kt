package com.myfawwaz.app.mybrain.domain.use_case.diary

import com.myfawwaz.app.mybrain.domain.repository.DiaryRepository
import javax.inject.Inject

class GetDiaryEntryUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository
) {
    suspend operator fun invoke(id: Int) = diaryRepository.getEntry(id)
}