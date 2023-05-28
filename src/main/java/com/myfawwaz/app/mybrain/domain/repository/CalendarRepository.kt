package com.myfawwaz.app.mybrain.domain.repository

import com.myfawwaz.app.mybrain.domain.model.Calendar
import com.myfawwaz.app.mybrain.domain.model.CalendarEvent

interface CalendarRepository {

    suspend fun getEvents(): List<CalendarEvent>

    suspend fun getCalendars(): List<Calendar>

    suspend fun addEvent(event: CalendarEvent)

    suspend fun deleteEvent(event: CalendarEvent)

    suspend fun updateEvent(event: CalendarEvent)
}