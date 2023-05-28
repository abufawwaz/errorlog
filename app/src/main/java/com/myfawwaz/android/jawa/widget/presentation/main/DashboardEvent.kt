package com.myfawwaz.android.jawa.widget.presentation.main

import com.myfawwaz.android.jawa.widget.domain.model.Task


sealed class DashboardEvent {
    data class ReadPermissionChanged(val hasPermission: Boolean) : DashboardEvent()
    data class UpdateTask(val task: Task) : DashboardEvent()
    object InitAll : DashboardEvent()
}