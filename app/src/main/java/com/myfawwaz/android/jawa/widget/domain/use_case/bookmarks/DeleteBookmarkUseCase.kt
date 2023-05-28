package com.myfawwaz.android.jawa.widget.domain.use_case.bookmarks

import com.myfawwaz.android.jawa.widget.domain.model.Bookmark
import com.myfawwaz.android.jawa.widget.domain.repository.BookmarkRepository
import javax.inject.Inject

class DeleteBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(bookmark: Bookmark) = bookmarkRepository.deleteBookmark(bookmark)
}