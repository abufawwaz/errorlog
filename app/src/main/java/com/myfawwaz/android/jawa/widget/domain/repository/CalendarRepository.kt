package com.myfawwaz.android.jawa.widget.domain.repository

import com.myfawwaz.android.jawa.widget.domain.model.Calendar
import com.myfawwaz.android.jawa.widget.domain.model.CalendarEvent

interface CalendarRepository {

    suspend fun getEvents(): List<CalendarEvent>

    suspend fun getCalendars(): List<Calendar>

    suspend fun addEvent(event: CalendarEvent)

    suspend fun deleteEvent(event: CalendarEvent)

    suspend fun updateEvent(event: CalendarEvent)
}