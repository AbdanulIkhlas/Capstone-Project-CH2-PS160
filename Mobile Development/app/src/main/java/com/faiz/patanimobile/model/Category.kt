package com.faiz.patanimobile.model

import androidx.annotation.DrawableRes
import com.faiz.patanimobile.R

data class Category(
    val name: String,
    val price: Int,
    @DrawableRes val icon: Int
)

val dummyCategory = listOf(
    "Produk 1" to R.drawable.sayur,
    "Produk 2" to R.drawable.sayur,
    "Produk 3" to R.drawable.sayur,
    "Produk 4" to R.drawable.sayur,
    "Produk 5" to R.drawable.sayur,
    "Produk 6" to R.drawable.sayur,
).map { Category(it.first, it.second, it.second) }