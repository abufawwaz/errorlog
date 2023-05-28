package com.myfawwaz.app.mybrain.domain.use_case.tasks

import com.myfawwaz.app.mybrain.domain.model.Task
import com.myfawwaz.app.mybrain.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchTasksUseCase @Inject constructor(
    private val tasksRepository: TaskRepository
) {
    operator fun invoke(query: String): Flow<List<Task>> {
        return tasksRepository.searchTasks(query)
    }
}