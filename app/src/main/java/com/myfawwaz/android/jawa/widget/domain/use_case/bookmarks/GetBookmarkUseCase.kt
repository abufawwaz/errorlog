package com.myfawwaz.android.jawa.widget.domain.use_case.bookmarks

import com.myfawwaz.android.jawa.widget.domain.repository.BookmarkRepository
import javax.inject.Inject

class GetBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(id: Int) = bookmarkRepository.getBookmark(id)
}