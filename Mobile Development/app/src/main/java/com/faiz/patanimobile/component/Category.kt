package com.faiz.patanimobile.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.faiz.patanimobile.R

data class Category (
    val name: String,
    @DrawableRes val icon: Int
)

val dummyCategory = listOf(
    "Kategori 1" to R.drawable.sayur,
    "Kategori 2" to R.drawable.sayur,
    "Kategori 1" to R.drawable.sayur,
    "Kategori 2" to R.drawable.sayur,
    "Kategori 1" to R.drawable.sayur,
    "Kategori 2" to R.drawable.sayur,
).map { Category(it.first, it.second) }