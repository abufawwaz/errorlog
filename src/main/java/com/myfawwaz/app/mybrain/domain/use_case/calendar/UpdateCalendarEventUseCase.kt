package com.myfawwaz.app.mybrain.domain.use_case.calendar

import android.content.Context
import android.content.Intent
import com.myfawwaz.app.mybrain.domain.model.CalendarEvent
import com.myfawwaz.app.mybrain.domain.repository.CalendarRepository
import com.myfawwaz.app.mybrain.presentation.glance_widgets.RefreshCalendarWidgetReceiver
import javax.inject.Inject

class UpdateCalendarEventUseCase @Inject constructor(
    private val calendarRepository: CalendarRepository,
    private val context: Context
) {
    suspend operator fun invoke(event: CalendarEvent) {
        calendarRepository.updateEvent(event)
        val updateIntent = Intent(context, RefreshCalendarWidgetReceiver::class.java)
        context.sendBroadcast(updateIntent)
    }
}