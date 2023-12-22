package com.faiz.patanistaticui.model

import androidx.annotation.DrawableRes
import com.faiz.patanistaticui.R

data class Shop (
    val name: String,
    @DrawableRes val image: Int
)

val dummyShop = listOf(
    "Toko" to R.drawable.adv,
    "Toko" to R.drawable.adv,
    "Toko" to R.drawable.toko,
    "Toko" to R.drawable.adv,
).map { Shop(it.first, it.second) }