package com.myfawwaz.android.jawa.widget.domain.use_case.tasks

import android.content.Context
import com.myfawwaz.android.jawa.widget.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateTaskCompletedUseCase @Inject constructor(
    private val tasksRepository: TaskRepository,
    private val context: Context
) {
    suspend operator fun invoke(taskId: Int, completed: Boolean) {
        tasksRepository.completeTask(taskId, completed)
        context.refreshTasksWidget()
    }
}