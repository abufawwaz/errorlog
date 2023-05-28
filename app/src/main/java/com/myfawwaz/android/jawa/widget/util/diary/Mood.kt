package com.myfawwaz.android.jawa.widget.util.diary

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.myfawwaz.android.jawa.widget.ui.theme.Blue
import com.myfawwaz.android.jawa.widget.ui.theme.Green
import com.myfawwaz.android.jawa.widget.ui.theme.Orange
import com.myfawwaz.android.jawa.widget.ui.theme.Purple
import com.myfawwaz.app.mybrain.R

enum class Mood(@DrawableRes val icon: Int, val color: Color, @StringRes val title: Int, val value: Int) {
    AWESOME(R.drawable.ic_very_happy, Green, R.string.awesome, 5),
    GOOD(R.drawable.ic_happy, Blue, R.string.good, 4),
    OKAY(R.drawable.ic_ok_face, Purple, R.string.okay, 3),
    BAD(R.drawable.ic_sad, Orange, R.string.bad, 2),
    TERRIBLE(R.drawable.ic_very_sad, Color.Red, R.string.terrible, 1)
}