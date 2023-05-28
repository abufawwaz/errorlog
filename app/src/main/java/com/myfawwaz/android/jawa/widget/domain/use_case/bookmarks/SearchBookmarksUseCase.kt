package com.myfawwaz.android.jawa.widget.domain.use_case.bookmarks

import com.myfawwaz.android.jawa.widget.domain.repository.BookmarkRepository
import javax.inject.Inject

class SearchBookmarksUseCase @Inject constructor(
    private val bookmarksRepository: BookmarkRepository
) {
    suspend operator fun invoke(query: String) = bookmarksRepository.searchBookmarks(query)
}