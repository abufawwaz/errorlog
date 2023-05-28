package com.myfawwaz.android.jawa.widget.presentation.calendar

import com.myfawwaz.android.jawa.widget.domain.model.Calendar
import com.myfawwaz.android.jawa.widget.domain.model.CalendarEvent

sealed class CalendarViewModelEvent {
    data class IncludeCalendar(val calendar: Calendar) : CalendarViewModelEvent()
    data class ReadPermissionChanged(val hasPermission: Boolean) : CalendarViewModelEvent()
    data class EditEvent(val event: CalendarEvent) : CalendarViewModelEvent()
    data class DeleteEvent(val event: CalendarEvent) : CalendarViewModelEvent()
    data class AddEvent(val event: CalendarEvent) : CalendarViewModelEvent()
    object ErrorDisplayed : CalendarViewModelEvent()
}
