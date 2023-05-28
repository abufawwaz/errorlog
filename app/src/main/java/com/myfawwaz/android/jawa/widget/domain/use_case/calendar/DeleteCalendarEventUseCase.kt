package com.myfawwaz.android.jawa.widget.domain.use_case.calendar

import android.content.Context
import android.content.Intent
import com.myfawwaz.android.jawa.widget.domain.model.CalendarEvent
import com.myfawwaz.android.jawa.widget.domain.repository.CalendarRepository
import com.myfawwaz.android.jawa.widget.presentation.glance_widgets.RefreshCalendarWidgetReceiver
import javax.inject.Inject

class DeleteCalendarEventUseCase @Inject constructor(
    private val calendarRepository: CalendarRepository,
    private val context: Context
) {
    suspend operator fun invoke(event: CalendarEvent) {
        calendarRepository.deleteEvent(event)
        val updateIntent = Intent(context, RefreshCalendarWidgetReceiver::class.java)
        context.sendBroadcast(updateIntent)
    }
}