package com.myfawwaz.app.mybrain.domain.use_case.alarm

import android.app.AlarmManager
import android.content.Context
import com.myfawwaz.app.mybrain.domain.model.Alarm
import com.myfawwaz.app.mybrain.domain.repository.AlarmRepository
import com.myfawwaz.app.mybrain.util.alarms.scheduleAlarm
import javax.inject.Inject

class AddAlarmUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository,
    private val context: Context
) {
    suspend operator fun invoke(alarm: Alarm) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.scheduleAlarm(alarm, context)
        alarmRepository.insertAlarm(alarm)
    }
}