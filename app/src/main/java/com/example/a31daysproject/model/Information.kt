package com.example.a31daysproject.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Information(
    @StringRes val stringId: Int,
    @DrawableRes val imageId: Int,
    @StringRes val stringId2: Int
)