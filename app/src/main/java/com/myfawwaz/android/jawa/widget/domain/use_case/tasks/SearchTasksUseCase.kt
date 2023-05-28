package com.myfawwaz.android.jawa.widget.domain.use_case.tasks

import com.myfawwaz.android.jawa.widget.domain.model.Task
import com.myfawwaz.android.jawa.widget.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchTasksUseCase @Inject constructor(
    private val tasksRepository: TaskRepository
) {
    operator fun invoke(query: String): Flow<List<Task>> {
        return tasksRepository.searchTasks(query)
    }
}