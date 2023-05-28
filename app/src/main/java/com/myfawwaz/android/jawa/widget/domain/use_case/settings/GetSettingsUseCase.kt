package com.myfawwaz.android.jawa.widget.domain.use_case.settings

import androidx.datastore.preferences.core.Preferences
import com.myfawwaz.android.jawa.widget.domain.repository.SettingsRepository
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    operator fun <T> invoke(key: Preferences.Key<T>, defaultValue: T) = settingsRepository.getSettings(key, defaultValue)
}