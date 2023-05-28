package com.myfawwaz.app.mybrain.domain.use_case.bookmarks

import com.myfawwaz.app.mybrain.domain.model.Bookmark
import com.myfawwaz.app.mybrain.domain.repository.BookmarkRepository
import javax.inject.Inject

class AddBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(bookmark: Bookmark) = bookmarkRepository.addBookmark(bookmark)
}