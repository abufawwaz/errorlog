package com.myfawwaz.android.jawa.widget.domain.repository

import com.myfawwaz.android.jawa.widget.domain.model.Alarm

interface AlarmRepository {

    suspend fun getAlarms(): List<Alarm>

    suspend fun insertAlarm(alarm: Alarm)

    suspend fun deleteAlarm(alarm: Alarm)

    suspend fun deleteAlarm(id: Int)
}