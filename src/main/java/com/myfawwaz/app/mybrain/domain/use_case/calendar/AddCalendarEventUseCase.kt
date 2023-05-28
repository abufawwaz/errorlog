package com.myfawwaz.app.mybrain.domain.use_case.calendar

import android.content.Context
import android.content.Intent
import com.myfawwaz.app.mybrain.domain.model.CalendarEvent
import com.myfawwaz.app.mybrain.domain.repository.CalendarRepository
import com.myfawwaz.app.mybrain.presentation.glance_widgets.RefreshCalendarWidgetReceiver
import javax.inject.Inject

class AddCalendarEventUseCase @Inject constructor(
    private val calendarEventRepository: CalendarRepository,
    private val context: Context
) {
    suspend operator fun invoke(calendarEvent: CalendarEvent) {
        calendarEventRepository.addEvent(calendarEvent)
        val updateIntent = Intent(context, RefreshCalendarWidgetReceiver::class.java)
        context.sendBroadcast(updateIntent)
    }

}