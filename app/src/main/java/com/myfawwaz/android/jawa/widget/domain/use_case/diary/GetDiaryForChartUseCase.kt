package com.myfawwaz.android.jawa.widget.domain.use_case.diary

import com.myfawwaz.android.jawa.widget.domain.model.DiaryEntry
import com.myfawwaz.android.jawa.widget.domain.repository.DiaryRepository
import com.myfawwaz.android.jawa.widget.util.date.inTheLast30Days
import com.myfawwaz.android.jawa.widget.util.date.inTheLastYear
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetDiaryForChartUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository
) {
    suspend operator fun invoke(monthly: Boolean) : List<DiaryEntry>{
        return diaryRepository
            .getAllEntries()
            .first()
            .filter {
                if (monthly) it.createdDate.inTheLast30Days()
                else it.createdDate.inTheLastYear()
            }
            .sortedBy { it.createdDate }
    }
}
