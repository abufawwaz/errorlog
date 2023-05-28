package com.myfawwaz.app.mybrain.presentation.bookmarks

import com.myfawwaz.app.mybrain.domain.model.Bookmark
import com.myfawwaz.app.mybrain.util.settings.ItemView
import com.myfawwaz.app.mybrain.util.settings.Order

sealed class BookmarkEvent {
    data class AddBookmark(val bookmark: Bookmark) : BookmarkEvent()
    data class GetBookmark(val bookmarkId: Int) : BookmarkEvent()
    data class SearchBookmarks(val query: String) : BookmarkEvent()
    data class UpdateOrder(val order: Order) : BookmarkEvent()
    data class UpdateView(val view: ItemView) : BookmarkEvent()
    data class UpdateBookmark(val bookmark: Bookmark) : BookmarkEvent()
    data class DeleteBookmark(val bookmark: Bookmark) : BookmarkEvent()
    object ErrorDisplayed: BookmarkEvent()
}
