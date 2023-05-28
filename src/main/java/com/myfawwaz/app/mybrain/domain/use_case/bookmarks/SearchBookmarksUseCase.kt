package com.myfawwaz.app.mybrain.domain.use_case.bookmarks

import com.myfawwaz.app.mybrain.domain.repository.BookmarkRepository
import javax.inject.Inject

class SearchBookmarksUseCase @Inject constructor(
    private val bookmarksRepository: BookmarkRepository
) {
    suspend operator fun invoke(query: String) = bookmarksRepository.searchBookmarks(query)
}