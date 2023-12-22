package com.faiz.patanistaticui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.faiz.patanistaticui.R

data class Category (
    val name: String,
    @DrawableRes val icon: Int
)

val dummyCategory = listOf(
    "Sayur-Sayuran" to R.drawable.sayuran,
    "Kacang-Kacangan" to R.drawable.kacangan,
    "Rempah" to R.drawable.rempahan,
    "Buah-Buahan" to R.drawable.buahan,
    "Bibit" to R.drawable.benihan,
    "Alat Tani" to R.drawable.alatan,
    "Umbi-Umbian" to R.drawable.umbian,
).map { Category(it.first, it.second) }