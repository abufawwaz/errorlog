package com.myfawwaz.app.mybrain.domain.use_case.tasks

import com.myfawwaz.app.mybrain.domain.repository.TaskRepository
import javax.inject.Inject

class GetTaskByIdUseCase @Inject constructor(
    private val tasksRepository: TaskRepository
) {
    suspend operator fun invoke(id: Int) = tasksRepository.getTaskById(id)
}