package com.myfawwaz.android.jawa.widget.util.bookmarks

import android.util.Patterns


fun String.isValidUrl(): Boolean {
    return Patterns.WEB_URL.matcher(this).matches()
}