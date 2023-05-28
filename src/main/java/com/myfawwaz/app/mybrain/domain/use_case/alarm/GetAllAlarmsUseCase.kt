package com.myfawwaz.app.mybrain.domain.use_case.alarm

import com.myfawwaz.app.mybrain.domain.repository.AlarmRepository
import javax.inject.Inject

class GetAllAlarmsUseCase @Inject constructor(
    private val alarmRepository: AlarmRepository
) {
    suspend operator fun invoke() = alarmRepository.getAlarms()
}