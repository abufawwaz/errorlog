package com.myfawwaz.android.jawa.widget.domain.use_case.tasks

import com.myfawwaz.android.jawa.widget.domain.model.Task
import com.myfawwaz.android.jawa.widget.domain.repository.TaskRepository
import com.myfawwaz.android.jawa.widget.util.settings.Order
import com.myfawwaz.android.jawa.widget.util.settings.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val tasksRepository: TaskRepository
) {
    operator fun invoke(order: Order): Flow<List<Task>> {
        return tasksRepository.getAllTasks().map { tasks ->
            when (order.orderType) {
                is OrderType.ASC -> {
                    when (order) {
                        is Order.Alphabetical -> tasks.sortedBy { it.title }
                        is Order.DateCreated -> tasks.sortedBy { it.createdDate }
                        is Order.DateModified -> tasks.sortedBy { it.updatedDate }
                        is Order.Priority -> tasks.sortedBy { it.priority }
                    }
                }
                is OrderType.DESC -> {
                    when (order) {
                        is Order.Alphabetical -> tasks.sortedByDescending { it.title }
                        is Order.DateCreated -> tasks.sortedByDescending { it.createdDate }
                        is Order.DateModified -> tasks.sortedByDescending { it.updatedDate }
                        is Order.Priority -> tasks.sortedByDescending { it.priority }
                    }
                }
            }
        }
    }
}