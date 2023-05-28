package com.myfawwaz.android.jawa.widget.domain.use_case.calendar

import com.myfawwaz.android.jawa.widget.domain.model.CalendarEvent
import com.myfawwaz.android.jawa.widget.domain.repository.CalendarRepository
import com.myfawwaz.android.jawa.widget.util.date.formatDateForMapping
import javax.inject.Inject

class GetAllEventsUseCase @Inject constructor(
    private val calendarRepository: CalendarRepository
) {
    suspend operator fun invoke(excluded: List<Int>, fromWidget: Boolean = false): Map<String, List<CalendarEvent>> {
        val events =  calendarRepository.getEvents()
            .filter { it.calendarId.toInt() !in excluded }
        return if (fromWidget)
            events.take(60).groupBy { event ->
                event.start.formatDateForMapping()
            }
        else
            events.groupBy { event ->
                event.start.formatDateForMapping()
            }

    }
}