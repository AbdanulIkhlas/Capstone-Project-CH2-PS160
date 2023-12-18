package com.faiz.patanimobile.model

import androidx.annotation.DrawableRes
import com.faiz.patanimobile.R

data class Menu(
    val image: Int,
    val title: String,
    val price: String,
)

data class Toko(
    val title: String,
    @DrawableRes val imageThumbId: Int
)

val dummyMenu = listOf(
    Menu(R.drawable.sayur, "Sayur Bayam", "Rp 28.000"),
    Menu(R.drawable.sayur, "Sayur Kacang", "Rp 18.000"),
    Menu(R.drawable.sayur, "Wortel", "Rp 20.000"),
    Menu(R.drawable.sayur, "Nikomori", "Rp 16.000"),
)
