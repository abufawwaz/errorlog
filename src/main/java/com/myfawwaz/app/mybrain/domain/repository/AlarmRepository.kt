package com.myfawwaz.app.mybrain.domain.repository

import com.myfawwaz.app.mybrain.domain.model.Alarm

interface AlarmRepository {

    suspend fun getAlarms(): List<Alarm>

    suspend fun insertAlarm(alarm: Alarm)

    suspend fun deleteAlarm(alarm: Alarm)

    suspend fun deleteAlarm(id: Int)
}