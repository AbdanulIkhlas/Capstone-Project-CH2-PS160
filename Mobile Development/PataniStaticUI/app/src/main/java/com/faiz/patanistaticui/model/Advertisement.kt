package com.faiz.patanistaticui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.faiz.patanistaticui.R

data class Advertisement(
    @StringRes val name: Int,
    @DrawableRes val image: Int
)

val dummyAdvertisement = listOf(
    R.string.iklan to R.drawable.adv,
    R.string.iklan2 to R.drawable.adv,
//    R.string.iklan to R.drawable.adv,
//    R.string.iklan to R.drawable.adv,
).map { Advertisement(it.first, it.second) }
