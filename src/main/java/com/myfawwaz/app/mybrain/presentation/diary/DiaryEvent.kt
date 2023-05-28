package com.myfawwaz.app.mybrain.presentation.diary

import com.myfawwaz.app.mybrain.domain.model.DiaryEntry
import com.myfawwaz.app.mybrain.util.settings.Order

sealed class DiaryEvent {
    data class AddEntry(val entry: DiaryEntry) : DiaryEvent()
    data class GetEntry(val entryId: Int) : DiaryEvent()
    data class SearchEntries(val query: String) : DiaryEvent()
    data class UpdateOrder(val order: Order) : DiaryEvent()
    data class UpdateEntry(val entry: DiaryEntry) : DiaryEvent()
    data class DeleteEntry(val entry: DiaryEntry) : DiaryEvent()
    data class ChangeChartEntriesRange(val monthly: Boolean) : DiaryEvent()
    object ErrorDisplayed: DiaryEvent()
}