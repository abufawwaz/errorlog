package com.myfawwaz.android.jawa.widget.domain.use_case.alarm

import com.myfawwaz.android.jawa.widget.domain.repository.AlarmRepository
import javax.inject.Inject

class GetAllAlarmsUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
) {
    suspend operator fun invoke() = alarmRepository.getAlarms()
}