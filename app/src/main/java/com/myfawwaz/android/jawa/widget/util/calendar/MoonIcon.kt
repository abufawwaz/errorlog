package com.myfawwaz.android.jawa.widget.util.calendar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.myfawwaz.android.jawa.widget.R

enum class Mood(@DrawableRes val icon: Int,
                //@StringRes val title: Int,
                val value: Int) {
    m00(R.drawable.m00, 5),
    GOOD(R.drawable.ic_happy,  4),
    OKAY(R.drawable.ic_ok_face, 3),
    BAD(R.drawable.ic_sad, 2),
    TERRIBLE(R.drawable.ic_very_sad,  1)
}