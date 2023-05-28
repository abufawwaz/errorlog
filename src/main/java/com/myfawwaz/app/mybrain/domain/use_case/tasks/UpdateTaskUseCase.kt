package com.myfawwaz.app.mybrain.domain.use_case.tasks

import android.content.Context
import com.myfawwaz.app.mybrain.domain.model.Task
import com.myfawwaz.app.mybrain.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val tasksRepository: TaskRepository,
    private val context: Context
) {
    suspend operator fun invoke(task: Task) {
        tasksRepository.updateTask(task)
        context.refreshTasksWidget()
    }
}