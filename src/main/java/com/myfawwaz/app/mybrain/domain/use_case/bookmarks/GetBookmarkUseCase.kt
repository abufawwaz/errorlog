package com.myfawwaz.app.mybrain.domain.use_case.bookmarks

import com.myfawwaz.app.mybrain.domain.repository.BookmarkRepository
import javax.inject.Inject

class GetBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(id: Int) = bookmarkRepository.getBookmark(id)
}