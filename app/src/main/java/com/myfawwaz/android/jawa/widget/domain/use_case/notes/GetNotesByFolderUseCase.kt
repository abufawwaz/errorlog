package com.myfawwaz.android.jawa.widget.domain.use_case.notes

import com.myfawwaz.android.jawa.widget.domain.repository.NoteRepository
import com.myfawwaz.android.jawa.widget.util.settings.Order
import com.myfawwaz.android.jawa.widget.util.settings.OrderType
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNotesByFolderUseCase @Inject constructor(
    private val notesRepository: NoteRepository
) {
    operator fun invoke(folderId: Int, order: Order) = notesRepository.getNotesByFolder(folderId).map { list ->
        when (order.orderType) {
            is OrderType.ASC -> {
                when (order) {
                    is Order.Alphabetical -> list.sortedWith(compareBy({!it.pinned}, { it.title }))
                    is Order.DateCreated -> list.sortedWith(compareBy({!it.pinned}, { it.createdDate }))
                    is Order.DateModified -> list.sortedWith(compareBy({!it.pinned}, { it.updatedDate }))
                    else -> list.sortedWith(compareBy({!it.pinned}, { it.updatedDate }))
                }
            }
            is OrderType.DESC -> {
                when (order) {
                    is Order.Alphabetical -> list.sortedWith(compareBy({it.pinned}, { it.title })).reversed()
                    is Order.DateCreated -> list.sortedWith(compareBy({it.pinned}, { it.createdDate })).reversed()
                    is Order.DateModified -> list.sortedWith(compareBy({it.pinned}, { it.updatedDate })).reversed()
                    else -> list.sortedWith(compareBy({it.pinned}, { it.updatedDate })).reversed()
                }
            }
        }
    }
}
