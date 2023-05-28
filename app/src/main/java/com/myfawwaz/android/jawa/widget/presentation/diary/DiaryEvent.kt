package com.myfawwaz.android.jawa.widget.presentation.diary

import com.myfawwaz.android.jawa.widget.domain.model.DiaryEntry
import com.myfawwaz.android.jawa.widget.util.settings.Order

sealed class DiaryEvent {
    data class AddEntry(val entry: DiaryEntry) : DiaryEvent()
    data class GetEntry(val entryId: Int) : DiaryEvent()
    data class SearchEntries(val query: String) : DiaryEvent()
   // data class SearchDate(val date: Long) : DiaryEvent()
    data class UpdateOrder(val order: Order) : DiaryEvent()
    data class UpdateEntry(val entry: DiaryEntry) : DiaryEvent()
    data class DeleteEntry(val entry: DiaryEntry) : DiaryEvent()
    data class ChangeChartEntriesRange(val monthly: Boolean) : DiaryEvent()
    object ErrorDisplayed: DiaryEvent()
}