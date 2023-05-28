package com.myfawwaz.android.jawa.widget.domain.use_case.tasks

import com.myfawwaz.android.jawa.widget.domain.repository.TaskRepository
import javax.inject.Inject

class GetTaskByIdUseCase @Inject constructor(
    private val tasksRepository: TaskRepository
) {
    suspend operator fun invoke(id: Int) = tasksRepository.getTaskById(id)
}